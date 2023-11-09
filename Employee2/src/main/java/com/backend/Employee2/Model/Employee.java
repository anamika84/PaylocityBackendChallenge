package com.backend.Employee2.Model;

import com.backend.Employee2.Dtos.DependentDto;
import com.backend.Employee2.Dtos.EmployeeDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

    @Data
    @Entity
    @Table(name= "EMPLOYEE")
    public class Employee {
        @Getter
        @jakarta.persistence.Id
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;
        private String firstName;
        private String lastName;
        private Date dateOfBirth;
        private Double grossSalary;

        @OneToMany
        @JoinColumn(name = "employee_id")
        private List<Dependent> dependentsEntities = new ArrayList<>();

        @OneToMany
        @JoinColumn(name =  "employee_id")
        private List<Salary> salaryList = new ArrayList<>();

        public void addDependents(Dependent dependents) {
            dependentsEntities.add(dependents);
        }

        public void addSalaries(Salary salary) {
            salaryList.add(salary);
        }

        public void removeDependents(Dependent dependents) {
            dependentsEntities.remove(dependents);
        }

        public void removeSalary(Salary salary) {
            salaryList.remove(salary);
        }

        public static Employee from(EmployeeDto employeeDto) {
            Employee employee = new Employee();
            employee.setFirstName(employeeDto.getFirstName());
            employee.setLastName(employeeDto.getLastName());
            employee.setGrossSalary(employeeDto.getGrossSalary());
            employee.setDateOfBirth(employeeDto.getDateOfBirth());

            // change dependent object
            if(employeeDto.getDependentsDtos() != null) {
                List<Dependent> dependentList = new ArrayList<>();
                List<DependentDto> dependentsDtos = employeeDto.getDependentsDtos();
                for (int i = 0; i < dependentsDtos.size(); i++) {
                    Dependent dependent = new Dependent();
                    dependent.setFirstName(dependentsDtos.get(i).getFirstName());
                    dependent.setLastName(dependentsDtos.get(i).getLastName());
                    dependent.setDateOfBirth(dependentsDtos.get(i).getDateOfBirth());
                    dependent.setRelationType(String.valueOf(dependentsDtos.get(i).getRelationship()));
                   // dependent.setEmployee(employee);
                    dependentList.add(dependent);
                }
                employee.setDependentsEntities(dependentList);
            }
            System.out.println("employee object " + employee);
            return  employee;
        }
    }
