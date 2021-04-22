package com.tistory.eclipse4j.core.jpa.service;

import com.tistory.eclipse4j.core.CoreApplication;
import com.tistory.eclipse4j.core.jpa.db1.service.CompanyFindService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import lombok.extern.slf4j.Slf4j;

@Disabled
@Slf4j
@SpringBootTest(classes = CoreApplication.class)
@ExtendWith(SpringExtension.class)
@ActiveProfiles(profiles = { "default"})
public class CompanyFindServiceTest {

	@Autowired
	private CompanyFindService service;

}
