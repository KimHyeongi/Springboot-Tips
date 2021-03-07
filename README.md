# Springboot-Tips


### Spring Boot 2.4.3.RELEASE
- TransactionalEventListener Tip 추가



### Spring Boot 2.1.7.RELEASE
- Multiple Datasources 를 지원하도록 한다. jpadb1, jpadb2
- 기존 단일 애플리케이션 빌드를 모듈로 분리시칸다. core, api
- flyway 설정을 추가한다.
- RestDocs 



# 우선 Docker(CE)를 로컬에 인스톨한다.
Mac :
https://store.docker.com/editions/community/docker-ce-desktop-mac
https://hub.docker.com/r/mysql/mysql-server/

docker-compose.yml 내 mysql 계정을 수정할 수 있다.

```
$> docker-compose up -d
```

이후 root 계정으로 진행.

## TransactionalEventListener 이벤트 리스너 등록.
```java
CompanyCodeEventListener : 이벤트리스너 
CompanyCodeUpdateEvent : 이벤트 ( GenericSpringEvent )
CompanyCreateService.updateCompanyCodeById(..) : 이벤트발행
```        


## JPA Query new XXXX(args..)를 사용하여 조회. 

@Query Annotation 등으로 특정 컬럼만 호출하도록 한다.
findById와 같이 일반적인 select에서는 해당 entity의 모든속성을 호출하게 되어있다. 이경우 불필요한 컬럼과 자식entity까지 가져오게 되므로 아래와 같이 처리하는게 좋을 수도 있다.

예) company table 에서 code 컬럼만 조회.
```java
@Query("select new Company(o.code) from Company o where o.id = :id")
Company findColumnById(@Param("id")Long id);
```


## JPA Lock - @Version 사용하기 
기본적으로 @Version사용시 낙관적 락이 적용된다.(LockModeType.NONE) 

A 사용자가 [OrderId 1번 조회후 ProductId를 10으로 변경] 
B 사용자가 [OrderId 1번 조회후 ProductId를 5으로 변경] 

먼저 A사용자가 ProductId의 변경을 시동하지만 이런저런 이슈로 하나의 TX가 15초 걸린다고 가정하고,
(http://localhost:8080/orders/1/products/10/update-tx-lock?ms=15000)

이후 B사용자는 걸림없이 ProductId를 5로 수정했다.
(http://localhost:8080/orders/1/products/10/update-tx-lock?ms=0)

이경우 A사용자는 먼저 find가 실행되어 해당 Version을 가지게 되고, B사용자는 늦게 find가 실행되어 A사용자보다 높은 Version을 갖게된다.

A사용자의 경우 OptimisticLock관련 오류가 throw되게 되고, B사용자의 ProductId로 업데이트된다.

중간에 find만 할경우는 Update가 완료된 B사용자의  ProductId가 적용되어 보이게된다.
(http://localhost:8080/orders/1?ms=0)


## JPA Spring Data Redis와 같이 사용하기
https://redis.io/download -> Redis 설치 
```java
application.properties 에 redis정보를 추가한다.
spring.redis.host=127.0.0.1
spring.redis.port=6379
```

설정 
```java
@Configuration
@EnableCaching
public class RedisCacheConfiguration {

	@Autowired
	private JedisConnectionFactory jedisConnectionFactory;

	...
}
```

## JPA Hibernate Second Level Cache / Query Cache 를 적용
- L2 캐시가 적용된 Company Entity 를 최초 조회시 select 구문 호출 확인.
- Company Update 이후 다시 select 구문이 실행 여부 확인.

```java
@..
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Company extends AuditingEntity implements Serializable {

	public Company(String code) {
		this.code = code;
	}

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
```

## JPA QueryDSL와 같이 사용하기
```java
public class CompanyRepositoryImpl extends QueryDslRepositorySupport implements CompanyRepositoryCustom {

    public CompanyRepositoryImpl() {
        super(Company.class);
    }

    @Override
    public Long findMaxId() {
        return from(QCompany.company).select(QCompany.company.id.max()).fetchOne();
    }

}
```

## @SqlResultSetMapping 를 이용한 커스텀 엔티티 적용.
```java
@SqlResultSetMappings({
        @SqlResultSetMapping(name = "SimpleCompany",
                classes = @ConstructorResult(targetClass = SimpleCompany.class,
                        columns = {
                                @ColumnResult(name = "code", type = String.class),
                                @ColumnResult(name = "name", type = String.class)
                        }))
})
...
public class Company extends AuditingEntity implements Serializable {
    ...
}
```
```java
    @Override
    public List<SimpleCompany> findSimpleCompanies() {
        StringBuilder queryBuilder = new StringBuilder("SELECT");
        queryBuilder.append(" code,");
        queryBuilder.append(" name");
        queryBuilder.append(" FROM company ");
        queryBuilder.append(" LIMIT 10 ");
        Query query = getEntityManager().createNativeQuery(queryBuilder.toString(), "SimpleCompany");
        List<SimpleCompany> results = query.getResultList();
        return results;
    }
```


## Hibernate Envers 적용


## CustomDto로 반환 받기
```java
public interface CompanyCustomDto {


	String getName();

	@Value("#{target.name} #{target.code}")
	String getNameAndCode();

}

public interface CompanyRepository exten .... {
	CompanyCustomDto findByName(String name);
	....
}
```
그러나 select 는 컬럼처리 되지 않는다.

```java
Hibernate: 
    /* select
        generatedAlias0 
    from
        Company as generatedAlias0 
    where
        generatedAlias0.name=:param0 */ select
            company0_.id as id1_0_,
            company0_.createdAt as createdA2_0_,
            company0_.createdBy as createdB3_0_,
            company0_.modifiedAt as modified4_0_,
            company0_.modifiedBy as modified5_0_,
            company0_.code as code6_0_,
            company0_.name as name7_0_,
            company0_.streetAddress as streetAd8_0_ 
        from
            company company0_ 
        where
            company0_.name=?
```     
