package com.backend.Employee2.Controller;

import com.backend.Employee2.Dtos.DependentDto;
import com.backend.Employee2.Dtos.EmployeeDto;
import com.backend.Employee2.Model.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface EmployeeImpl {
    public ResponseEntity<EmployeeDto> addEmployee(final EmployeeDto employeeDto);
    public ResponseEntity<EmployeeDto> getEmployee(final Long id);
    public ResponseEntity<List<EmployeeDto>> getEmployees();
    public ResponseEntity<EmployeeDto> deleteEmployee(final Long id);

   public ResponseEntity<EmployeeDto> addDependentToEmployee( final Long id,  final Long dependentId);

    public ResponseEntity<EmployeeDto> removeDependentToEmployee(final Long employeeId, final Long dependentId);
}
