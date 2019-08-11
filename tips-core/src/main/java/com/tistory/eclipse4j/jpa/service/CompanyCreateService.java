package com.tistory.eclipse4j.jpa.service;

import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tistory.eclipse4j.jpa.db1.entity.Company;
import com.tistory.eclipse4j.jpa.db1.repository.CompanyRepository;

@Service
public class CompanyCreateService {
    @Autowired
    private CompanyRepository companyRepository;

    public Company save(Company company){
        return companyRepository.save(company);
    }

    public Company updateById(Long id, String name){
        Company orgCompany = companyRepository.findById(id).orElseThrow(()->new EntityNotFoundException(String.valueOf(id)));
        orgCompany.setName(name);
        return companyRepository.save(orgCompany);
    }
}
