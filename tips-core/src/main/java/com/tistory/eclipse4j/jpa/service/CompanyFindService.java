package com.tistory.eclipse4j.jpa.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tistory.eclipse4j.jpa.db1.dto.CompanyCustomDto;
import com.tistory.eclipse4j.jpa.db1.dto.CompanyDto;
import com.tistory.eclipse4j.jpa.db1.entity.Company;
import com.tistory.eclipse4j.jpa.db1.repository.CompanyRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CompanyFindService {

	@Autowired
	private CompanyRepository companyRepository;


	@Transactional
	public CompanyCustomDto findByName(String name) {
		return companyRepository.findByName(name);
	}

	public Company findById(Long id) {
		return companyRepository.findById(id).orElseThrow(EntityNotFoundException::new);
	}

	public Company findColumnById(Long id) {
		return companyRepository.findColumnById(id);
	}

	@Cacheable(cacheNames = "Company", key = "#id")
	public CompanyDto findCacheDataById(Long id) {
//		Company company = companyRepository.findById(id).orElseThrow(EntityNotFoundException::new);
		Company company = companyRepository.findById(id).orElse(new Company());
		return CompanyDto.map(company);
	}
}
