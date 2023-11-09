package com.backend.Employee2;

import com.backend.Employee2.Dtos.Relationship;
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

import java.text.DecimalFormat;
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
        salary.setTotalSalary(4567.0);
        salary.setId(1);
        List<Salary> salariesByEmployeeId = new ArrayList<>();
        salariesByEmployeeId.add(salary);
        when(salaryRepository.findSalariesByEmployee_Id(any())).thenReturn(salariesByEmployeeId);
        Salary salaryByEmployeeId = salaryService.getSalaryByEmployeeId(2L);
        Assertions.assertEquals(4567.0, salaryByEmployeeId.getTotalSalary());
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
        employee.setGrossSalary(1000000.0);
        employee.setDependentsEntities(dependentList);
        when(employeeRepository.findById(any())).thenReturn(Optional.of(employee));
        salaryService.addSalaryToEmployee(employee.getId());
        Mockito.verify(employeeRepository, times(1)).findById(any());
    }

    @Test
    public void calculateFinalSalaryForAnEmployeeWithMultipleChild() {
        List<Dependent> dependentList = new ArrayList<>();
        Dependent dependent = new Dependent();
        dependent.setRelationType("Child");
        Dependent dependent1 = new Dependent();
        dependent1.setRelationType("Child");
        dependentList.add(dependent1);
        dependentList.add(dependent);

        Employee employee = new Employee();
        employee.setFirstName("fn");
        employee.setLastName("ln");
        employee.setGrossSalary(1000000.0);
        employee.setDependentsEntities(dependentList);
        when(employeeRepository.findById(any())).thenReturn(Optional.of(employee));
        Double calculateFinalSalary = salaryService.calculateFinalSalary(employee);

        double totalSalary = employee.getGrossSalary() - (employee.getGrossSalary()*2)/100;
        double totalDependentDeduction = (double) (Relationship.Child.getAmount() * 12)/26;
        totalSalary = totalSalary - 2* totalDependentDeduction;
        totalSalary = totalSalary - (double) ((1000 * 12) /26);
        DecimalFormat df = new DecimalFormat("#.00");
        Assertions.assertEquals(df.format(totalSalary), df.format(calculateFinalSalary));
    }

    @Test
    public void calculateFinalSalaryWhenNoDepdendents() {
        List<Dependent> dependentList = new ArrayList<>();

        Employee employee = new Employee();
        employee.setFirstName("fn");
        employee.setLastName("ln");
        employee.setGrossSalary(50000.0);
        employee.setDependentsEntities(dependentList);
        when(employeeRepository.findById(any())).thenReturn(Optional.of(employee));
        Double calculateFinalSalary = salaryService.calculateFinalSalary(employee);
        Assertions.assertTrue(calculateFinalSalary <= 1424.0);
    }

    @Test
    public void calculateFinalSalaryWhenLessThen80kSalary() {
        List<Dependent> dependentList = new ArrayList<>();
        Dependent dependent = new Dependent();
        dependent.setRelationType("Child");
        Dependent dependent1 = new Dependent();
        dependent1.setRelationType("Child");
        dependentList.add(dependent1);
        dependentList.add(dependent);

        Employee employee = new Employee();
        employee.setFirstName("fn");
        employee.setLastName("ln");
        employee.setGrossSalary(50000.0);
        employee.setDependentsEntities(dependentList);
        when(employeeRepository.findById(any())).thenReturn(Optional.of(employee));
        Double calculateFinalSalary = salaryService.calculateFinalSalary(employee);

        double totalDependentDeduction = (double) (Relationship.Child.getAmount() * 12)/26;
        double totalSalary = employee.getGrossSalary() - 2* totalDependentDeduction;
        totalSalary = totalSalary - (double) ((1000 * 12) /26);
        DecimalFormat df = new DecimalFormat("#.00");
        Assertions.assertEquals(df.format(totalSalary), df.format(calculateFinalSalary));
    }

    @Test
    public void calculateFinalSalaryWithChildAndElders() {
        List<Dependent> dependentList = new ArrayList<>();
        Dependent dependent = new Dependent();
        dependent.setRelationType("Child");
        Dependent dependent1 = new Dependent();
        dependent1.setRelationType("Elder");
        dependentList.add(dependent1);
        dependentList.add(dependent);

        Employee employee = new Employee();
        employee.setFirstName("fn");
        employee.setLastName("ln");
        employee.setGrossSalary(50000.0);
        employee.setDependentsEntities(dependentList);
        when(employeeRepository.findById(any())).thenReturn(Optional.of(employee));
        Double calculateFinalSalary = salaryService.calculateFinalSalary(employee);

        double totalDependentDeduction = (double) (Relationship.Child.getAmount() * 12)/26 + (double) (Relationship.Elder.getAmount() * 12) /26;
        double totalSalary = employee.getGrossSalary() - totalDependentDeduction;
        totalSalary = totalSalary - (double) ((1000 * 12) /26);
        DecimalFormat df = new DecimalFormat("#.00");
        Assertions.assertEquals(df.format(totalSalary), df.format(calculateFinalSalary));
    }

    @Test
    public void checkIfSalaryIsInNegative() {
        try {
            List<Dependent> dependentList = new ArrayList<>();
            Dependent dependent = new Dependent();
            dependent.setRelationType("Child");
            Dependent dependent1 = new Dependent();
            dependent1.setRelationType("Elder");
            dependentList.add(dependent1);
            dependentList.add(dependent);

            Employee employee = new Employee();
            employee.setFirstName("fn");
            employee.setLastName("ln");
            employee.setGrossSalary(100.0);
            employee.setDependentsEntities(dependentList);
            when(employeeRepository.findById(any())).thenReturn(Optional.of(employee));
            Double calculateFinalSalary = salaryService.calculateFinalSalary(employee);

            double totalDependentDeduction = (double) (Relationship.Child.getAmount() * 12) / 26 + (double) (Relationship.Elder.getAmount() * 12) / 26;
            double totalSalary = employee.getGrossSalary() - totalDependentDeduction;
            totalSalary = totalSalary - (double) ((1000 * 12) / 26);
            DecimalFormat df = new DecimalFormat("#.00");
            Assertions.fail();
        } catch (Exception e) {
            System.out.println("catch the exception");
        }
    }
}
