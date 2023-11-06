package com.backend.Employee2.Service;

import com.backend.Employee2.Dtos.Relationship;
import com.backend.Employee2.Model.Dependent;
import com.backend.Employee2.Model.Employee;
import com.backend.Employee2.Model.Exception.SalaryException;
import com.backend.Employee2.Model.Salary;
import com.backend.Employee2.Repository.EmployeeRepository;
import com.backend.Employee2.Repository.SalaryRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalaryService {

    public final SalaryRepository salaryRepository;
    public final EmployeeRepository employeeRepository;


    public SalaryService(SalaryRepository salaryRepository, EmployeeRepository employeeRepository) {
        this.salaryRepository = salaryRepository;
        this.employeeRepository = employeeRepository;
    }

    public Salary addSalary(Salary salary) {
        return salaryRepository.save(salary);
    }

    public Optional<Salary> getSalaryById(Long salaryId) {
        Optional<Salary> salaryById = salaryRepository.findById(salaryId);
        return salaryById;
    }

    public Salary getSalaryByEmployeeId(Long id) {
        List<Salary> salariesByEmployeeId = salaryRepository.findSalariesByEmployee_Id(id);
        return salariesByEmployeeId.get(0);
    }

    @Transactional
    public Salary addSalaryToEmployee(Long employee_id) {
        Optional<Employee> employee = employeeRepository.findById(employee_id);
       Double calculateFinalSalary = calculateFinalSalary(employee.get());
        Salary salary = new Salary();
        salary.setTotalSalary(calculateFinalSalary);
        salary.setEmployee(employee.get());
        return salaryRepository.save(salary);
    }

    public Double calculateFinalSalary(Employee emp) {
        // get employee salary
        Double totalSalary = emp.getGrossSalary();
        System.out.println("total slary -- " +totalSalary);
        //check if total salary is more than 80,000
        int MAX_SALARY = 80000;
        int TOTAL_PAYCHECK = 26;

        if(totalSalary > MAX_SALARY) {
            System.out.println("inside big salary");
            totalSalary = totalSalary - (totalSalary*2)/100 ;
        }
        // divide salary in equal 26 parts
        double byWeekly = totalSalary / TOTAL_PAYCHECK;
        // get all the dependents
        List<Dependent> dependentsEntities = emp.getDependentsEntities();
        for( Dependent dependent : dependentsEntities) {

            String relationType = dependent.getRelationType();
            byWeekly = switch (relationType) {
                case "Child" -> byWeekly - Relationship.Child.getAmount();
                case "Spouse" -> byWeekly - Relationship.Spouse.getAmount();
                case "Elder" -> byWeekly - Relationship.Elder.getAmount();
                default -> byWeekly;
            };
        }
        byWeekly = byWeekly - 200;

        if(byWeekly < 0) {
            throw new SalaryException(totalSalary);
        }
        return byWeekly;
    }

}
