package com.tistory.eclipse4j.jpa.test;

import org.apache.commons.lang3.StringUtils;
import org.springframework.test.context.ActiveProfilesResolver;

public class ActiveProfileResolver implements ActiveProfilesResolver {

	@Override
	public String[] resolve(Class<?> testClass) {
		String profile = StringUtils.defaultIfEmpty(System.getProperty("profile"), "");
		return new String[] {profile};

	}
}
