package com.tistory.eclipse4j.jpa.service;

import com.tistory.eclipse4j.jpa.db1.dto.CompanyDto;
import com.tistory.eclipse4j.jpa.db1.entity.Company;
import com.tistory.eclipse4j.jpa.db1.repository.CompanyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
public class CompanyCacheableFindService {

	private final CompanyRepository companyRepository;

	public CompanyCacheableFindService(CompanyRepository companyRepository, CompanySecondCacheableFindService companySecondCacheableFindService){
		this.companyRepository = companyRepository;
	}

	@Cacheable(cacheNames = "cached_company_id", key = "#id")
	public CompanyDto findCacheDataById(Long id) {
		Company company = companyRepository.findById(id).orElse(new Company());
		return CompanyDto.map(company);
	}

}
