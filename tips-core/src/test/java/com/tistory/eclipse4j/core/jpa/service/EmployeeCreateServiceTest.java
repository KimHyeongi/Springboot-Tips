package com.tistory.eclipse4j.core.jpa.service;

import com.tistory.eclipse4j.core.CoreApplication;
import com.tistory.eclipse4j.core.jpa.db1.entity.Company;
import com.tistory.eclipse4j.core.jpa.db1.entity.Employee;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
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
public class EmployeeCreateServiceTest {

	@Autowired
	private EmployeeCreateService service;

	@Test
	@DisplayName("사원 삭제 Soft Delete")
	public void 사원삭제() {
		Employee persist = service.save(Employee.builder()
				.company(Company.builder().id(1L).build())
				.name("이름")
				.build());

		service.deleteById(persist.getId());

		Assertions.assertTrue(persist.isDeleted());
	}

}
