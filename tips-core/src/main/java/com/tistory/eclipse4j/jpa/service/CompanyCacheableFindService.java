package com.tistory.eclipse4j.jpa.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tistory.eclipse4j.jpa.db1.dto.CompanyDto;
import com.tistory.eclipse4j.jpa.db1.entity.Company;
import com.tistory.eclipse4j.jpa.db1.repository.CompanyRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CompanyCacheableFindService {

	private final CompanyRepository companyRepository;

	@Cacheable(cacheNames = "cached_company_id", key = "#id")
	public CompanyDto findCacheDataById(Long id) {
		Company company = companyRepository.findById(id).orElse(new Company());
		return CompanyDto.map(company);
	}

}
