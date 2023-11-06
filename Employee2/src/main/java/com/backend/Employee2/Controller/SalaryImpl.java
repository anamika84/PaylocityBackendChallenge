package com.backend.Employee2.Controller;

import com.backend.Employee2.Dtos.SalaryDto;
import com.backend.Employee2.Model.Salary;
import org.springframework.http.ResponseEntity;

public interface SalaryImpl {
    public ResponseEntity<SalaryDto> getEmployeeSalary (final Long id);
    public ResponseEntity<SalaryDto> addSalary(final SalaryDto salaryDto);
    public ResponseEntity<SalaryDto> addSalaryToEmployee(final Long employeeId);
}
