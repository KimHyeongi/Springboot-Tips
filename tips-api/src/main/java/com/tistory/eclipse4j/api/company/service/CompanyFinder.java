package com.tistory.eclipse4j.api.company.service;

import com.tistory.eclipse4j.api.company.data.CompanyInfo;
import com.tistory.eclipse4j.core.jpa.db1.entity.Company;
import com.tistory.eclipse4j.core.jpa.db1.service.CompanyFindService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyFinder {
    private final CompanyFindService companyFindService;

    public CompanyInfo findCacheableById(Long companyId){
        Optional<Company> company = companyFindService.findById(companyId);
        return company.map(CompanyInfo::of).get();
    }

}
