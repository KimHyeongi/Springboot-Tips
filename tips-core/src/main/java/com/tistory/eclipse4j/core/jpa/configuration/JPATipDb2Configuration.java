package com.tistory.eclipse4j.core.jpa.configuration;

import java.sql.SQLException;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"com.tistory.eclipse4j.jpa.db2"}, 
	transactionManagerRef = "jpadb2TransactionManager",
	entityManagerFactoryRef = "jpadb2EntityManagerFactory")
public class JPATipDb2Configuration {

	@Bean(name = "jpadb2DataSource")
	@ConfigurationProperties(prefix = "spring.datasource.hikari.jpadb2")
	public DataSource jpadb2DataSource() throws SQLException {
		HikariDataSource hikariDataSource = new HikariDataSource();
		return hikariDataSource;
	}

	@PersistenceContext(unitName = "jpadb2")
	@Bean(name = "jpadb2EntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean jpadb2EntityManagerFactory(EntityManagerFactoryBuilder builder,
		@Qualifier("jpadb2DataSource") DataSource jpadb2DataSource) {
		return builder.dataSource(jpadb2DataSource).packages("com.tistory.eclipse4j.jpa.db2")
				.persistenceUnit("jpadb2").build();
	}

	@Bean(name = "jpadb2TransactionManager")
	public PlatformTransactionManager jpadb2TransactionManager(
		@Qualifier("jpadb2EntityManagerFactory") EntityManagerFactory jpadb2EntityManagerFactory) {
		return new JpaTransactionManager(jpadb2EntityManagerFactory);
	}
}
