package com.tistory.eclipse4j.core.jpa.service;

import com.tistory.eclipse4j.core.CoreApplication;
import com.tistory.eclipse4j.core.jpa.db1.service.EmployeeCreateService;
import lombok.extern.slf4j.Slf4j;
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

}
