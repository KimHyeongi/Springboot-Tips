package com.tistory.eclipse4j.core.company.service;

import com.tistory.eclipse4j.core.jpa.db1.entity.Company;
import lombok.Getter;

@Getter
public class CompanyCodeUpdateEvent  {
    private String message;
    private Company source;

    public CompanyCodeUpdateEvent(Company source){
        this.message = "회사 코드가 수정 되었습니다.";
        this.source = source;
    }

    public CompanyCodeUpdateEvent(Company source, String message){
        this.message = message;
        this.source = source;
    }

    // condition test
    public boolean updated(){
        return true;
    }
}
