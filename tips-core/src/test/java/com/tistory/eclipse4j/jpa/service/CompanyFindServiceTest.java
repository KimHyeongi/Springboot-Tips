package com.tistory.eclipse4j.jpa.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.tistory.eclipse4j.CoreApplication;
import com.tistory.eclipse4j.jpa.test.ActiveProfileResolver;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringRunner.class)
@ActiveProfiles(inheritProfiles = false, resolver = ActiveProfileResolver.class)
@SpringBootTest(classes = CoreApplication.class)
public class CompanyFindServiceTest {

	@Autowired
	private CompanyFindService service;

	@Test
	public void test() {
		log.debug("Find 1");
		service.findCacheDataById(1L);
		
		log.debug("Find 2");
		service.findCacheDataById(1L);
	}

}
