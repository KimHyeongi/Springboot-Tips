package com.tistory.eclipse4j.jpa.db1.repository;

import java.util.List;

import com.tistory.eclipse4j.jpa.db1.entity.SimpleCompany;

public interface CompanyRepositoryCustom {
    Long findMaxId();

    SimpleCompany findSimpleCompanyById(Long id);

    List<SimpleCompany> findSimpleCompanies();
}
