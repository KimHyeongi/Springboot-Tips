package com.tistory.eclipse4j.jpa.service;

import javax.persistence.EntityNotFoundException;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.tistory.eclipse4j.jpa.db1.dto.CompanyDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tistory.eclipse4j.jpa.db1.dto.CompanyCustomDto;
import com.tistory.eclipse4j.jpa.db1.entity.Company;
import com.tistory.eclipse4j.jpa.db1.repository.CompanyRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional(readOnly = true)
public class CompanyFindService {

	private final CompanyRepository companyRepository;
	private final CompanyCacheableFindService companyCacheableFindService;
	private final CompanySecondCacheableFindService companySecondCacheableFindService;

	public CompanyFindService(CompanyRepository companyRepository, CompanyCacheableFindService companyCacheableFindService, CompanySecondCacheableFindService companySecondCacheableFindService){
		this.companyRepository = companyRepository;
		this.companyCacheableFindService = companyCacheableFindService;
		this.companySecondCacheableFindService = companySecondCacheableFindService;
	}

	public CompanyCustomDto findByName(String name) {
		return companyRepository.findByName(name);
	}

	public Company findById(Long id) {
		return companyRepository.findById(id).orElseThrow(EntityNotFoundException::new);
	}

	public Company findColumnById(Long id) {
		return companyRepository.findColumnById(id);
	}

	@HystrixCommand(fallbackMethod = "findSecondCacheableDtoById")
	public CompanyDto findCacheableDtoById(Long id) {
		return companyCacheableFindService.findCacheDataById(id);
	}

	public CompanyDto findSecondCacheableDtoById(Long id) {
		return companySecondCacheableFindService.findCaffeineCacheDataById(id);
	}
}
