package com.backend.Employee2;

import com.backend.Employee2.Model.Dependent;
import com.backend.Employee2.Model.Employee;
import com.backend.Employee2.Model.Salary;
import com.backend.Employee2.Repository.EmployeeRepository;
import com.backend.Employee2.Repository.SalaryRepository;
import com.backend.Employee2.Service.SalaryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@SpringBootTest
public class SalaryTest {

    @Mock
    public SalaryRepository salaryRepository;

    @Mock
    public  EmployeeRepository employeeRepository;

    @InjectMocks
    private SalaryService salaryService;

    @Test
    public void getSalaryByEmployeeIdTest() {
        Salary salary = new Salary();
        salary.setFinalSalary(4567.0);
        salary.setId(1);
        List<Salary> salariesByEmployeeId = new ArrayList<>();
        salariesByEmployeeId.add(salary);
        when(salaryRepository.findSalariesByEmployee_Id(any())).thenReturn(salariesByEmployeeId);
        Salary salaryByEmployeeId = salaryService.getSalaryByEmployeeId(2L);
        Assertions.assertEquals(4567.0, salaryByEmployeeId.getFinalSalary());
    }

    @Test
    public void addSalaryToEmployeeTest() {
        List<Dependent> dependentList = new ArrayList<>();
        Dependent dependent = new Dependent();
        dependent.setRelationType("Child");
        dependentList.add(dependent);

        Employee employee = new Employee();
        employee.setFirstName("fn");
        employee.setLastName("ln");
        employee.setSalary(1000000.0);
        employee.setDependentsEntities(dependentList);
        when(employeeRepository.findById(any())).thenReturn(Optional.of(employee));
        salaryService.addSalaryToEmployee(employee.getId());
        Mockito.verify(employeeRepository, times(1)).findById(any());
        Mockito.verify(salaryService, times(1)).calculateFinalSalary(any());
    }

    @Test
    public void calculateFinalSalaryForAnEmployee() {
        List<Dependent> dependentList = new ArrayList<>();
        Dependent dependent = new Dependent();
        dependent.setRelationType("Child");
        dependentList.add(dependent);

        Employee employee = new Employee();
        employee.setFirstName("fn");
        employee.setLastName("ln");
        employee.setSalary(1000000.0);
        employee.setDependentsEntities(dependentList);
        when(employeeRepository.findById(any())).thenReturn(Optional.of(employee));
        Double calculateFinalSalary = salaryService.calculateFinalSalary(employee);

    }
}
