package com.tistory.eclipse4j.api.controller;

import com.tistory.eclipse4j.core.jpa.db1.dto.CompanyDto;
import com.tistory.eclipse4j.core.jpa.db1.entity.Company;
import com.tistory.eclipse4j.core.jpa.service.CompanyCreateService;
import com.tistory.eclipse4j.core.jpa.service.CompanyFindService;
import com.tistory.eclipse4j.core.jpa.service.ReservationTxService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
public class CompanyController {

	private CompanyFindService companyFindService;
	private CompanyCreateService companyCreateService;

	public CompanyController(CompanyFindService findService, CompanyCreateService createService) {
		this.companyFindService = findService;
		this.companyCreateService = createService;
	}

	@GetMapping(path = "/companies/{companyId}")
	public CompanyDto find(@PathVariable("companyId") Long companyId) {
		CompanyDto companyDto = companyFindService.findCacheableDtoById(companyId);
		return companyDto;
	}

	@GetMapping(path = "/companies/{companyId}/update")
	public HttpStatus update(@PathVariable("companyId") Long companyId,
			@RequestParam("companyName") String companyName) {
		companyCreateService.updateCompanyNameById(companyId, companyName);
		return HttpStatus.OK;
	}

	@PostMapping(path = "/companies/create")
	public HttpStatus save(@RequestBody Company company) {
		companyCreateService.create(company);
		return HttpStatus.CREATED;
	}
}
