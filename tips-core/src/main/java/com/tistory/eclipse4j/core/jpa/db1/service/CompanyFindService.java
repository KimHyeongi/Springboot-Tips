package com.tistory.eclipse4j.core.jpa.db1.service;

import com.tistory.eclipse4j.core.jpa.db1.entity.Company;
import com.tistory.eclipse4j.core.jpa.db1.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CompanyFindService {

	private final CompanyRepository companyRepository;

	public Optional<Company> findById(Long companyId) {
		return companyRepository.findById(companyId);
	}

}
