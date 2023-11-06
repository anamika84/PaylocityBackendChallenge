package com.backend.Employee2;

import com.backend.Employee2.Model.Dependent;
import com.backend.Employee2.Model.Employee;
import com.backend.Employee2.Repository.DependentRepository;
import com.backend.Employee2.Repository.EmployeeRepository;
import com.backend.Employee2.Service.DependentsService;
import com.backend.Employee2.Service.EmployeeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@SpringBootTest
class EmployeeServiceTest   {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private DependentsService dependentsService;

    @InjectMocks
    private EmployeeService employeeService;

    @Test
    void addEmployeeTest() {
        Employee emp = new Employee();
        emp.setId(123);
        emp.setFirstName("firstName");
        emp.setLastName("lastName");
        Employee employee = employeeService.addEmployee(emp);
    }

    @Test
    Employee getEmployeeById() {
        Optional<Employee> emp = Optional.of(new Employee());
        emp.get().setLastName("lastName");
        emp.get().setGrossSalary(190000.0);
        emp.get().setFirstName("firstName");

        when(employeeRepository.findById(any())).thenReturn(emp);
        Employee employeeById = employeeService.getEmployeeById(1L);
        Assertions.assertEquals("firstName", employeeById.getFirstName());
        Assertions.assertEquals("lastName", employeeById.getLastName());
        return  employeeById;
    }

    @Test
    void addDependentToEmployeeTest () {
        Dependent dependents = new Dependent();
        dependents.setLastName("depLastName");
        dependents.setRelationType("Child");
        dependents.setFirstName("depFirstName");
        Employee employee = getEmployeeById();
        when(dependentsService.getDependent(any())).thenReturn(dependents);
        Employee emplWithDependents = employeeService.addDependentToEmployee(employee.getId(), 2L);
        Assertions.assertEquals(emplWithDependents.getDependentsEntities().size() , 1);
        Assertions.assertEquals("depFirstName", emplWithDependents.getDependentsEntities().get(0).getFirstName());
        Assertions.assertEquals("depLastName", emplWithDependents.getDependentsEntities().get(0).getLastName());
        Mockito.verify(dependentsService, times(1)).getDependent(any());
    }
}
