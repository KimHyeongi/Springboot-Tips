package com.tistory.eclipse4j.jpa.db1.dto;

import java.io.Serializable;
import java.util.function.Function;

import com.tistory.eclipse4j.jpa.db1.entity.Employee;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@SuppressWarnings("serial")
@Builder
@Getter
public class EmployeeDto implements Serializable {

    private Long id;

    private String name;

    public static Function<Employee, EmployeeDto> convertEmployeeDto = new Function<Employee, EmployeeDto>() {
        @Override
        public EmployeeDto apply(Employee o){
            return EmployeeDto.builder().id(o.getId()).name(o.getName()).build();
        }
    };

}
