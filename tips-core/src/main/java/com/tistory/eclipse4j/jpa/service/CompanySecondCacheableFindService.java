package com.tistory.eclipse4j.jpa.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.tistory.eclipse4j.jpa.db1.dto.CompanyDto;
import com.tistory.eclipse4j.jpa.db1.entity.Company;
import com.tistory.eclipse4j.jpa.db1.repository.CompanyRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CompanySecondCacheableFindService {

    private final CompanyRepository companyRepository;

    @Cacheable(cacheNames = "cached_redis_caffeine_company_id", key = "#id", cacheManager = "caffeineCacheManager")
    public CompanyDto findCaffeineCacheDataById(Long id) {
        Company company = companyRepository.findById(id).orElse(new Company());
        return CompanyDto.map(company);
    }

}
