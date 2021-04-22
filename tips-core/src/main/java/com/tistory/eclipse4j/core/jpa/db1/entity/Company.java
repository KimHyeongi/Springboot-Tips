package com.tistory.eclipse4j.core.jpa.db1.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@SuppressWarnings("serial")
@SqlResultSetMappings({
        @SqlResultSetMapping(name = "SimpleCompany",
                classes = @ConstructorResult(targetClass = SimpleCompany.class,
                        columns = {
                                @ColumnResult(name = "code", type = String.class),
                                @ColumnResult(name = "name", type = String.class)
                        }))
})
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "company")
@Audited
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Company extends AuditingEntity implements Serializable {

    public static final Company DEFAULT = Company.builder().build();

    public Company(String code) {
        this.code = code;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    @NotAudited
    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Employee> employees;

    @Column(name = "streetAddress")
    private String streetAddress;
}
