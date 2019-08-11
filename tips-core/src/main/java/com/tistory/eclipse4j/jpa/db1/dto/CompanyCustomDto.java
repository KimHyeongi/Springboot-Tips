package com.tistory.eclipse4j.jpa.db1.dto;

import org.springframework.beans.factory.annotation.Value;

public interface CompanyCustomDto {


	String getName();

	@Value("#{target.name} #{target.code}")
	String getNameAndCode();

}
