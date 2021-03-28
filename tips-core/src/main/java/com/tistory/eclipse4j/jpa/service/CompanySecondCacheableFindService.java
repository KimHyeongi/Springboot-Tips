package com.tistory.eclipse4j.jpa.service;

import com.tistory.eclipse4j.jpa.db1.dto.CompanyDto;
import com.tistory.eclipse4j.jpa.db1.entity.Company;
import com.tistory.eclipse4j.jpa.db1.repository.CompanyRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class CompanySecondCacheableFindService {

    private final CompanyRepository companyRepository;

    public CompanySecondCacheableFindService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Cacheable(cacheNames = "cached_redis_caffeine_company_id", key = "#id", cacheManager = "caffeineCacheManager")
    public CompanyDto findCaffeineCacheDataById(Long id) {
        Company company = companyRepository.findById(id).orElse(new Company());
        return CompanyDto.map(company);
    }

}
