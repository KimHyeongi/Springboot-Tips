package com.tistory.eclipse4j.core.jpa.db1.repository;

import com.tistory.eclipse4j.core.jpa.db1.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface CompanyRepository extends CompanyRepositoryCustom, JpaRepository<Company, Long>, QuerydslPredicateExecutor<Company> {

}
