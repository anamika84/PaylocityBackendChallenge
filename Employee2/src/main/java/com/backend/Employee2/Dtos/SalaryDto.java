package com.backend.Employee2.Dtos;

import com.backend.Employee2.Model.Dependent;
import com.backend.Employee2.Model.Employee;
import com.backend.Employee2.Model.Salary;
import lombok.Data;

import java.util.List;

@Data
public class SalaryDto {
    private Long id;
    private Double totalSalary;
    private Double  grossSalary;
    private Integer totalDependents;
    private Integer children;
    private Integer elders;

    public static SalaryDto from(Salary salary) {
        SalaryDto salaryDto = new SalaryDto();
        if(salary != null) {
            int count =0;
            int children =0;
            int elders = 0;
            salaryDto.setId(salary.getId());
            salaryDto.setTotalSalary(salary.getTotalSalary());
            salaryDto.setGrossSalary(salary.getEmployee().getGrossSalary());
            List<Dependent> dependentsEntities = salary.getEmployee().getDependentsEntities();
            for(Dependent d : dependentsEntities) {
                if(d.getRelationType().equals("Child")) {
                    children++;
                } else if( d.getRelationType().equals("Elder")) {
                    elders ++;
                }
                count++;
            }
            salaryDto.setTotalDependents(count);

        }
        return salaryDto;
    }
}
