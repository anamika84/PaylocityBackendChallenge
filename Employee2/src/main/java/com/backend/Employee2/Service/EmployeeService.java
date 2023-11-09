package com.backend.Employee2.Service;

import com.backend.Employee2.Model.Dependent;
import com.backend.Employee2.Model.Employee;
import com.backend.Employee2.Model.Exception.EmployeeNotFoundException;
import com.backend.Employee2.Model.Exception.PartnerExistException;
import com.backend.Employee2.Repository.DependentRepository;
import com.backend.Employee2.Repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
    public class EmployeeService {

        private final EmployeeRepository employeeRepository;

        private final DependentsService dependentsService;


        public EmployeeService(EmployeeRepository employeeRepository, DependentRepository dependentRepository, DependentsService dependentsService) {
            this.employeeRepository = employeeRepository;
            this.dependentsService = dependentsService;
        }

        public List<Employee> getAllEmployees() {
           return StreamSupport.stream(employeeRepository.findAll().spliterator(), false).collect(Collectors.toList());
        }
         public Employee addEmployee(Employee employee) {
            return  employeeRepository.save(employee);
        }

        public Employee getEmployeeById(Long id) {
            System.out.println("id " +id);
           return employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
        }

    public Employee deleteEmployee(Long id) {
        Employee employee = getEmployeeById(id);
        employeeRepository.delete(employee);
        return employee;
    }

        @Transactional
        public Employee addDependentToEmployee(Long employee_id, Long dependent_id) {
            Employee employee = getEmployeeById(employee_id);
            Dependent dependents = dependentsService.getDependent(dependent_id);

            // validation to check both spouse and domestic partner should not link to an employee
            boolean validRelation = validatePartner(employee, dependents);
            if(validRelation) {
                employee.addDependents(dependents);
            } else {
                throw new PartnerExistException(employee_id);
            }
            return employee;
        }

        private boolean validatePartner( Employee employee, Dependent dependent) {
            System.out.println("validPartner" + employee.getDependentsEntities().size());
                boolean spouse = employee.getDependentsEntities().stream().anyMatch(e -> e.getRelationType().equals("Spouse"));
                boolean domesticPartner = employee.getDependentsEntities().stream().anyMatch(e -> e.getRelationType().equals("DomesticPartner"));

                if(dependent.getRelationType().equals("DomesticPartner") && spouse) {
                    return false;
                }
                if(dependent.getRelationType().equals("Spouse") && domesticPartner) {
                    return  false;
                }
            return true;
        }

        public Employee removeDependentFromEmployee(Long depedent_id, Long employee_id) {
            Employee employee = getEmployeeById(employee_id);
            Dependent dependent = dependentsService.getDependent(depedent_id);
            employee.removeDependents(dependent);
            return  employee;
        }

}

