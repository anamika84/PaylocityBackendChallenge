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
  // calculate by weekly salary, by deciding it in 26 equal parts
    public Double calculateFinalSalary(Employee emp) {
        int MAX_SALARY = 80000;
        // get employee salary
        Double totalSalary = emp.getGrossSalary();


        //check if total salary is more than 80,000
        if(totalSalary > MAX_SALARY) {
            totalSalary = totalSalary - (totalSalary*2)/100 ;
        }

        // get all the dependents
        List<Dependent> dependentsEntities = emp.getDependentsEntities();
        double totalDetectableAmount = 0D;
        for( Dependent dependent : dependentsEntities) {

            String relationType = dependent.getRelationType();
            switch(relationType) {
                case "Child" :
                     totalDetectableAmount = (double) (Relationship.Child.getAmount() * 12)/26;
                     break;
                case "Spouse" :
                     totalDetectableAmount = (double) (Relationship.Spouse.getAmount() * 12)/26;
                     break;
                case "Elder" :
                     totalDetectableAmount = (double) (Relationship.Elder.getAmount() * 12)/26;
                     break;
                case "DomesticPartner" :
                     totalDetectableAmount = (double) (Relationship.DomesticPartner.getAmount() * 12)/26;
                     break;
                default :
                    totalSalary = totalSalary - 0;
                    break;

            };

            totalSalary = totalSalary - totalDetectableAmount;
        }

        // benefits
        Double baseCostByWeekly = (double) (1000*12 / 26);
        totalSalary = totalSalary - baseCostByWeekly;

        if(totalSalary < 0) {
            throw new SalaryException(totalSalary);
        }
        return totalSalary;
    }

}
