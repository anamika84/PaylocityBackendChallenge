package com.backend.Employee2.Controller;

import com.backend.Employee2.Dtos.EmployeeDto;
import com.backend.Employee2.Model.Employee;
import com.backend.Employee2.Service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController implements EmployeeImpl{
    private final EmployeeService employeeService;


    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    @PostMapping
    public ResponseEntity<EmployeeDto> addEmployee(@RequestBody final EmployeeDto employeeDto) {
        Employee employee = employeeService.addEmployee(Employee.from(employeeDto));
        return new ResponseEntity<>(EmployeeDto.from(employee), HttpStatus.OK);
    }

    /**
     * get employee detail by passing employee id
     * @param id
     * @return Employee
     */
    @Override
    @GetMapping(value = "{id}")
    public Employee getEmployee(@PathVariable final Long id) {
        return employeeService.getEmployeeById(id);
    }

    /**
     * add a dependent to en employee by passing employee id , dependent id and dependent detials in request body
     * @param employeeId
     * @param dependentId
     * @return RespouseEntity<Employeedto>
     */
    @PostMapping(value = "{employeeId}/dependents/{dependentId}/add")
    public ResponseEntity<EmployeeDto> addDependentToEmployee(@PathVariable final Long employeeId, @PathVariable final Long dependentId) {
        Employee employee = employeeService.addDependentToEmployee(employeeId, dependentId);
        return new ResponseEntity<>(EmployeeDto.from(employee), HttpStatus.OK);
    }

    /**
     * remove a dependent from an employye
     * @param employeeId
     * @param dependentId
     * @return ResponseEntity<EmployeeDto>
     */
    @DeleteMapping(value = "{employeeId}/dependents/{dependentId}/delete")
    public ResponseEntity<EmployeeDto> removeDependentToEmployee(@PathVariable final Long employeeId, @PathVariable final Long dependentId) {
        Employee employee = employeeService.removeDependentFromEmployee(employeeId, dependentId);
        return new ResponseEntity<>(EmployeeDto.from(employee), HttpStatus.OK);
    }

}

