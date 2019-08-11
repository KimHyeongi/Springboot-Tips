package com.tistory.eclipse4j.jpa.db1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import com.tistory.eclipse4j.jpa.db1.dto.CompanyCustomDto;
import com.tistory.eclipse4j.jpa.db1.entity.Company;

public interface CompanyRepository extends CompanyRepositoryCustom, JpaRepository<Company, Long>, QuerydslPredicateExecutor<Company> {

    @Query("select new Company(o.code) from Company o where o.id = :id")
    Company findColumnById(@Param("id") Long id);


    CompanyCustomDto findByName(String name);
}
