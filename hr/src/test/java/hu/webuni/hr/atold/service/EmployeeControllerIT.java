package hu.webuni.hr.atold.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient;

import hu.webuni.hr.atold.dto.EmployeeDto;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class EmployeeControllerIT {
	
	private static final String BASE_URI = "/api/employees";
	
	@Autowired
	WebTestClient webTestClient;
	
	@Test
	void newEmployeeListed() throws Exception {
		
		EmployeeDto emp = new EmployeeDto(50, "Integrációs Imi", "Gyakornok", 30000, LocalDateTime.parse("2022-01-03T08:00:00"));
		
		
		List<EmployeeDto> employeesBefore = getEmployees();
		createNewEmployee(emp);
		List<EmployeeDto> employeesAfter = getEmployees();
		
		assertThat(!employeesBefore.contains(emp));
		assertThat(employeesAfter.contains(emp));
		
	}
	
	@Test
	void overwriteEmployee() throws Exception {
		
		EmployeeDto emp = new EmployeeDto(1, "Integrációs Imi", "Gyakornok", 30000, LocalDateTime.parse("2022-01-03T08:00:00"));
		
		
		List<EmployeeDto> employeesBefore = getEmployees();
		EmployeeDto returnedEmployee = overwriteExistingEmployee(emp);
		List<EmployeeDto> employeesAfter = getEmployees();
		
		
		assertThat(returnedEmployee.equals(emp));
		assertThat(!employeesBefore.contains(emp));
		assertThat(employeesAfter.contains(emp));
		
	}
	
	
	
	

	private void createNewEmployee(EmployeeDto newEmployee) {
		
		webTestClient
			.post()
			.uri(BASE_URI)
			.bodyValue(newEmployee)
			.exchange()
			.expectStatus()
			.isOk();	
		
	}
	
	private EmployeeDto overwriteExistingEmployee(EmployeeDto newEmployee) {
		
		return webTestClient
					.put()
					.uri(BASE_URI + "/" + newEmployee.getId())
					.bodyValue(newEmployee)
					.exchange()
					.expectStatus()
					.isOk()
					.expectBody(EmployeeDto.class)
					.returnResult()
					.getResponseBody();
	}

	private List<EmployeeDto> getEmployees() {
		
		return webTestClient
					.get()
					.uri(BASE_URI)
					.exchange()
					.expectStatus()
					.isOk()
					.expectBodyList(EmployeeDto.class)
					.returnResult()
					.getResponseBody();
	}
	
}
