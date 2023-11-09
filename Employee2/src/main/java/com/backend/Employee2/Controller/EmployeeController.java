package com.backend.Employee2.Controller;

import com.backend.Employee2.Dtos.DependentDto;
import com.backend.Employee2.Dtos.EmployeeDto;
import com.backend.Employee2.Model.Dependent;
import com.backend.Employee2.Model.Employee;
import com.backend.Employee2.Service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    @GetMapping(value = "{id}")
    public ResponseEntity<EmployeeDto> getEmployee(@PathVariable final Long id) {
        Employee employee = employeeService.getEmployeeById(id);
        return new ResponseEntity<>(EmployeeDto.from(employee), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<EmployeeDto>> getEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        List<EmployeeDto> employeeDtos = employees.stream().map(EmployeeDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(employeeDtos, HttpStatus.OK);
    }

    @Override
    @DeleteMapping(value = "{id}")
    public ResponseEntity<EmployeeDto> deleteEmployee(Long id) {
        Employee employee = employeeService.deleteEmployee(id);
        return new ResponseEntity<>(EmployeeDto.from(employee), HttpStatus.OK);
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

