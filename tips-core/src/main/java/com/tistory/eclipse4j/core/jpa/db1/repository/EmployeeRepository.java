package com.tistory.eclipse4j.core.jpa.db1.repository;

import com.tistory.eclipse4j.core.jpa.db1.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface EmployeeRepository extends JpaRepository<Employee, Long>, QuerydslPredicateExecutor<Employee> {

}
