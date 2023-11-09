package com.backend.Employee2.Model;

import com.backend.Employee2.Dtos.SalaryDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.Id;


@Data
@Entity
@Table(name="SALARY")
public class Salary {

    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    Double totalSalary;
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="employee_id")
    private Employee employee;


    public static Salary from(SalaryDto salaryDto) {
            Salary salary = new Salary();
            salary.setId(salaryDto.getId());
            salary.setTotalSalary(salaryDto.getTotalSalary());
             return  salary;

    }
}