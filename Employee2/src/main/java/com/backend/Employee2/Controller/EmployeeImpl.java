package com.backend.Employee2.Controller;

import com.backend.Employee2.Dtos.EmployeeDto;
import com.backend.Employee2.Model.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface EmployeeImpl {
    public ResponseEntity<EmployeeDto> addEmployee(final EmployeeDto employeeDto);
    public Employee getEmployee(final Long id);
}
