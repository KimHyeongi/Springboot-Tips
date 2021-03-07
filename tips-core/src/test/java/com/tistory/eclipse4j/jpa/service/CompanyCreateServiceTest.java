package com.tistory.eclipse4j.jpa.service;

import com.tistory.eclipse4j.CoreApplication;
import com.tistory.eclipse4j.jpa.db1.entity.Company;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@SpringBootTest(classes = CoreApplication.class)
@ExtendWith(SpringExtension.class)
@ActiveProfiles(profiles = { "default"})
@Rollback(value = false)
@Transactional
public class CompanyCreateServiceTest {

	@Autowired
	private CompanyCreateService service;

	@Test
	public void 회사명수정() {
		service.updateCompanyNameById(1L, "name");
	}

	@Test
	public void 회사코드수정() {
		Company newCompany = service.newCompany(Company.builder().name("Name").code("Code").build());
		service.updateCompanyCodeById(newCompany.getId(), "코드");
	}

}
