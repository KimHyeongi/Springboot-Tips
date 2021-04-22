package com.tistory.eclipse4j.api.company.data;

import com.tistory.eclipse4j.core.annotation.RestDocClass;
import com.tistory.eclipse4j.core.annotation.RestDocProperty;
import com.tistory.eclipse4j.core.jpa.db1.entity.Company;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Getter
@Builder
@RestDocClass(description = "회사정보조회")
public class CompanyInfo implements Serializable {

    @RestDocProperty(description = "회사ID")
    private Long id;

    @RestDocProperty(description = "회사명")
    private String name;

    public static CompanyInfo of(Company company){
        return CompanyInfo.builder()
                .id(company.getId())
                .name(company.getName())
                .build();
    }
}
