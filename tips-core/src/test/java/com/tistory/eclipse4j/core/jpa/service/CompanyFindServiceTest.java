package com.tistory.eclipse4j.api.jpa.service;

import com.tistory.eclipse4j.core.CoreApplication;
import com.tistory.eclipse4j.core.jpa.service.CompanyFindService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.tistory.eclipse4j.api.CoreApplication;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest(classes = CoreApplication.class)
@ExtendWith(SpringExtension.class)
@ActiveProfiles(profiles = { "default"})
public class CompanyFindServiceTest {

	@Autowired
	private CompanyFindService service;

	@Test
	public void test() {
		log.debug("Find 1");
		service.findCacheableDtoById(1L);
		
		log.debug("Find 2");
		service.findCacheableDtoById(1L);
	}

}
