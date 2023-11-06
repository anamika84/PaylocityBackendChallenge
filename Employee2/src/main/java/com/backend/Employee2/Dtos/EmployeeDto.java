package com.backend.Employee2.Dtos;

import com.backend.Employee2.Model.Employee;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
    public class EmployeeDto {
        private Long id;
        private String firstName;
        private String LastName;
        private List<DependentDto> dependentsDtos = new ArrayList<>();
        private Double grossSalary;
        private Date dateOfBirth;

    public EmployeeDto(String firstName, String lastName, List<DependentDto> dependentsDtos, Double salary, Date dateOfBirth) {
        this.firstName = firstName;
        LastName = lastName;
        this.dependentsDtos = dependentsDtos;
        this.grossSalary = salary;
        this.dateOfBirth = dateOfBirth;
    }

    public static EmployeeDto from(Employee employee) {
            EmployeeDto employeeDto = new EmployeeDto(employee.getFirstName(), employee.getLastName(), employee.getDependentsEntities().stream().map(DependentDto :: from).collect(Collectors.toList()), employee.getGrossSalary(), employee.getDateOfBirth());
            employeeDto.setId(employee.getId());
            employeeDto.setFirstName(employee.getFirstName());
            employeeDto.setLastName(employee.getLastName());
            employeeDto.setGrossSalary(employeeDto.getGrossSalary());
            employeeDto.setDependentsDtos(employee.getDependentsEntities().stream().map(DependentDto :: from).collect(Collectors.toList()));
            return employeeDto;
        }

    }
