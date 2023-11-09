package com.backend.Employee2;

import com.backend.Employee2.Model.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class Employee2ApplicationTests {

	@LocalServerPort
	private int port;

	private String baseUrl = "http://localhost";

	private static RestTemplate restTemplate;
	private TestH2Repository testH2Repository;

	@BeforeAll
	public static void init() {
		restTemplate =  new RestTemplate();
	}

	@BeforeEach
	public void setUp() {
		baseUrl = baseUrl.concat(":").concat(port+"").concat("/api/v1/employee");
	}

	@Test
	public void testAddEmployee() {
		Employee employee = new Employee();
		employee.setGrossSalary(90000.0);
		employee.setLastName("gupta");
		employee.setFirstName("Anamika");
		employee.setDateOfBirth(new Date());

		Employee response = restTemplate.postForObject(baseUrl, employee, employee.getClass());
		Assertions.assertEquals("Anamika", response.getFirstName());

	}

	@Test
	@Sql(statements = "INSERT INTO Employee (id,first_name,last_name,date_of_birth,gross_salary) values (4, 'Mirnal', 'singh', null, 34000.0)", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements =  "Delete from Employee where first_name = 'Mirnal'", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
	public void testGetEmployees() {
		List<Employee> employees = restTemplate.getForObject(baseUrl, List.class);
        assert employees != null;
        Assertions.assertEquals(1, employees.size());
	}

	@Test
	@Sql(statements = "INSERT INTO Employee (id,first_name,last_name,date_of_birth,gross_salary) values (4, 'Mirnal', 'singh', null, 34000.0)", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements =  "Delete from Employee where first_name = 'Mirnal'", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
	public void findEmployeeById() {
		Employee employee = restTemplate.getForObject(baseUrl + "/{id}", Employee.class, 4);
		Assertions.assertAll(
				() -> Assertions.assertNotNull(employee),
				() -> Assertions.assertEquals(4, employee.getId())
		);
	}

	@Test
	@Sql(statements = "INSERT INTO Employee (id,first_name,last_name,date_of_birth,gross_salary) values (4, 'Mirnal', 'singh', null, 34000.0)", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
	public void return404WhenRecordDoesntExist() {
		try {
			Employee response = restTemplate.getForObject(baseUrl + "/{id}", Employee.class, 4);
			Assertions.fail();
		} catch (Exception e) {
			Assertions.assertTrue(e.getMessage().contains("404"));
		}
	}

	@Test
	void contextLoads() {
	}

}
