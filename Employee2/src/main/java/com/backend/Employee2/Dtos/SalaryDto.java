package com.backend.Employee2.Dtos;

import com.backend.Employee2.Model.Employee;
import com.backend.Employee2.Model.Salary;
import lombok.Data;

@Data
public class SalaryDto {
    private Long id;
    private Double totalSalary;
    private Employee employee;

    public static SalaryDto from(Salary salary) {
        SalaryDto salaryDto = new SalaryDto();
        if(salary != null) {
            salaryDto.setId(salary.getId());
            salaryDto.setTotalSalary(salary.getTotalSalary());
            salaryDto.setEmployee(salary.getEmployee());
        }
        return salaryDto;
    }
}
