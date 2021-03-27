package com.tistory.eclipse4j.jpa.service;

import com.tistory.eclipse4j.jpa.db1.entity.Employee;
import com.tistory.eclipse4j.jpa.db1.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

@Service
public class EmployeeCreateService {

    private final EmployeeRepository repository;

    public EmployeeCreateService(EmployeeRepository repository){
        this.repository = repository;
    }

    public Employee save(Employee employee){
        return repository.save(employee);
    }
    public void deleteById(Long id){
        repository.deleteById(id);
    }
}
