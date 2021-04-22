package com.tistory.eclipse4j.api.company.data;

import com.tistory.eclipse4j.core.jpa.db1.entity.Company;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CompanyInfo {
    private Long id;
    private String name;

    public static CompanyInfo of(Company company){
        return CompanyInfo.builder()
                .id(company.getId())
                .name(company.getName())
                .build();
    }
}
