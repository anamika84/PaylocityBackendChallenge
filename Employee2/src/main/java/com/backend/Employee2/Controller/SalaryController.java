package com.backend.Employee2.Controller;

import com.backend.Employee2.Dtos.SalaryDto;
import com.backend.Employee2.Model.Salary;
import com.backend.Employee2.Service.SalaryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/salary")
public class SalaryController implements SalaryImpl {
    private final SalaryService salaryService;

    public SalaryController(SalaryService salaryService) {
        this.salaryService = salaryService;
    }

    @Override
    @PostMapping
    public ResponseEntity<SalaryDto> addSalary(@RequestBody final SalaryDto salaryDto) {
        Salary salary = salaryService.addSalary(Salary.from(salaryDto));
        return new ResponseEntity<>(SalaryDto.from(salary), HttpStatus.OK);
    }

    @Override
    @GetMapping(value = "{id}")
    public ResponseEntity<SalaryDto> getEmployeeSalary(@PathVariable final Long id) {
        Salary salaryByEmployeeId = salaryService.getSalaryByEmployeeId(id);
        return new ResponseEntity<>(SalaryDto.from(salaryByEmployeeId), HttpStatus.OK);
    }

    @Override
    @PostMapping(value = "{employeeId}/add")
    public ResponseEntity<SalaryDto> addSalaryToEmployee(@PathVariable final Long employeeId) {
        Salary salary = salaryService.addSalaryToEmployee(employeeId);
        return  new  ResponseEntity<>(SalaryDto.from(salary), HttpStatus.OK);
    }
}


