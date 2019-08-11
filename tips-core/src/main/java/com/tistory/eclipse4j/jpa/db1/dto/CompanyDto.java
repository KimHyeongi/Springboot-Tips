package com.tistory.eclipse4j.jpa.db1.dto;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import com.tistory.eclipse4j.jpa.db1.entity.Company;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CompanyDto {

    private Long id;

    private String name;

    private String code;

    private List<EmployeeDto> employeeDtos;

    private String streetAddress;

    public static CompanyDto build(Company company) {
        if(Objects.isNull(company)){
            return CompanyDto.builder().build();
        }
        List<EmployeeDto> employeeDtos = Optional.ofNullable(company.getEmployees()).orElse(Collections.emptyList()).stream().map(EmployeeDto.convertEmployeeDto).collect(Collectors.toList());
        return CompanyDto.builder().id(company.getId())
                .name(company.getName())
                .code(company.getCode())
                .streetAddress(company.getStreetAddress())
                .employeeDtos(employeeDtos)
                .build();
    }
}