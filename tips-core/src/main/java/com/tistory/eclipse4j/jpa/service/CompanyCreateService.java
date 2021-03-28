package com.tistory.eclipse4j.jpa.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tistory.eclipse4j.jpa.db1.entity.Company;
import com.tistory.eclipse4j.jpa.db1.repository.CompanyRepository;
import com.tistory.eclipse4j.jpa.service.event.CompanyCodeUpdateEvent;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CompanyCreateService {

	private final CompanyRepository companyRepository;
	private final ApplicationEventPublisher applicationEventPublisher;

	public Company newCompany(Company company) {
		return companyRepository.save(company);
	}

	public Company updateCompanyNameById(Long id, String name) {
		Company orgCompany = companyRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
		orgCompany.setName(name);
		return companyRepository.save(orgCompany);
	}

	@Transactional
	public Company updateCompanyCodeById(Long id, String code) {
		Company orgCompany = companyRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
		orgCompany.setCode(code);
		Company updatedCompany = companyRepository.save(orgCompany);
		applicationEventPublisher.publishEvent(new CompanyCodeUpdateEvent(updatedCompany));
		return updatedCompany;
	}

}
