package com.tistory.eclipse4j.core.jpa.service;

import javax.persistence.EntityNotFoundException;

import com.tistory.eclipse4j.core.jpa.db1.dto.CompanyDto;
import com.tistory.eclipse4j.core.jpa.db1.entity.Company;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.tistory.eclipse4j.core.jpa.db1.dto.CompanyCustomDto;
import com.tistory.eclipse4j.core.jpa.db1.repository.CompanyRepository;

import lombok.RequiredArgsConstructor;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CompanyFindService {

	private final CompanyRepository companyRepository;
	private final CompanyCacheableFindService companyCacheableFindService;
	private final CompanySecondCacheableFindService companySecondCacheableFindService;

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
		log.info("Redis Cache 호출하기");
		return companyCacheableFindService.findCacheDataById(id);
	}

	public CompanyDto findSecondCacheableDtoById(Long id) {
		log.info("Caffeine Cache 호출하기");
		return companySecondCacheableFindService.findCaffeineCacheDataById(id);
	}
}
