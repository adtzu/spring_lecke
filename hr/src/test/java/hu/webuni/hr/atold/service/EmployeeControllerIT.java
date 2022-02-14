package hu.webuni.hr.atold.service;

import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.reactive.server.WebTestClient;

import hu.webuni.hr.atold.dto.EmployeeDto;
import hu.webuni.hr.atold.model.Employee;
import hu.webuni.hr.atold.model.Position;
import hu.webuni.hr.atold.repository.EmployeeRepository;
import hu.webuni.hr.atold.repository.PositionRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
public class EmployeeControllerIT {
	
	private static final String BASE_URI = "/api/employees";
	
	@Autowired
	WebTestClient webTestClient;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	PositionRepository positionRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	private String user = "user";
	private String pass = "pass";

	@BeforeEach
	public void initDb() {
		
		if(employeeRepository.findByUsername(user).isEmpty())
		{
			Employee emp = new Employee();
			emp.setUsername(user);
			emp.setPassword(passwordEncoder.encode(pass));
			
			employeeRepository.save(emp);
		}
	}
	
	@Test
	void newEmployeeListed() throws Exception {
		
		Position pos = new Position("Gyakornok", 200000);
		positionRepository.save(pos);
		EmployeeDto emp = new EmployeeDto(50, "Integrációs Imi", pos, 30000, LocalDateTime.parse("2022-01-03T08:00:00"), null);
		
		
		List<EmployeeDto> employeesBefore = getEmployees();
		createNewEmployee(emp);
		List<EmployeeDto> employeesAfter = getEmployees();
		
		assertThat(!employeesBefore.contains(emp));
		assertThat(employeesAfter.contains(emp));
		
	}
	
	@Test
	void newInvalidEmployee() throws Exception {
		
		Position pos = new Position("Gyakornok", 200000);
		positionRepository.save(pos);
		
		EmployeeDto empInvalid = new EmployeeDto(1, "", pos, -10, LocalDateTime.parse("2023-01-03T08:00:00"), null);		
		
		List<EmployeeDto> employeesBefore = getEmployees();
		createInvalidEmployee(empInvalid);
		List<EmployeeDto> employeesAfter = getEmployees();
		
		assertThat(!employeesBefore.contains(empInvalid));
		assertThat(!employeesAfter.contains(empInvalid));
		
	}
	
	@Test
	void overwriteEmployee() throws Exception {
		
		Position pos = new Position("Gyakornok", 200000);
		positionRepository.save(pos);
		EmployeeDto emp = new EmployeeDto(1, "Integrációs Imi", pos, 30000, LocalDateTime.parse("2022-01-03T08:00:00"), null);
		
		
		List<EmployeeDto> employeesBefore = getEmployees();
		EmployeeDto returnedEmployee = overwriteExistingEmployee(emp);
		List<EmployeeDto> employeesAfter = getEmployees();
		
		
		assertThat(returnedEmployee.equals(emp));
		assertThat(!employeesBefore.contains(emp));
		assertThat(employeesAfter.contains(emp));
		
	}
	
	@Test
	void overwriteWithInvalidEmployee() throws Exception {
		
		Position pos = new Position("Gyakornok", 200000);
		positionRepository.save(pos);
		EmployeeDto emp = new EmployeeDto(1, "Integrációs Imi", pos, 30000, LocalDateTime.parse("2022-01-03T08:00:00"), null);
		EmployeeDto saved = createNewEmployee(emp);
		
		EmployeeDto empInvalid = new EmployeeDto(1, "", pos, 30000, LocalDateTime.parse("2022-01-03T08:00:00"), null);
		empInvalid.setId(saved.getId());
		
		List<EmployeeDto> employeesBefore = getEmployees();
		overwriteExistingWithInvalidEmployee(empInvalid);
		
		assertThat(!employeesBefore.contains(empInvalid));
		
	}
	
	private void createInvalidEmployee(EmployeeDto newEmployee) {
			
		webTestClient
			.post()
			.uri(BASE_URI)
			.headers(headers -> headers.setBearerAuth(BASE_URI))
			.bodyValue(newEmployee)
			.exchange()
			.expectStatus()
			.isBadRequest();	
			
	}

	private EmployeeDto createNewEmployee(EmployeeDto newEmployee) {
		
		return webTestClient
			.post()
			.uri(BASE_URI)
			.headers(headers -> headers.setBasicAuth(user, pass))
			.bodyValue(newEmployee)
			.exchange()
			.expectStatus()
			.isOk()
			.expectBody(EmployeeDto.class)
			.returnResult()
			.getResponseBody();
		
	}
	
	private EmployeeDto overwriteExistingEmployee(EmployeeDto newEmployee) {
		
		return webTestClient
					.put()
					.uri(BASE_URI + "/" + newEmployee.getId())
					.headers(headers -> headers.setBasicAuth(user, pass))
					.bodyValue(newEmployee)
					.exchange()
					.expectStatus()
					.isOk()
					.expectBody(EmployeeDto.class)
					.returnResult()
					.getResponseBody();
	}
	
	private void overwriteExistingWithInvalidEmployee(EmployeeDto newEmployee) {
		
		webTestClient
			.put()
			.uri(BASE_URI + "/" + newEmployee.getId())
			.headers(headers -> headers.setBasicAuth(user, pass))
			.bodyValue(newEmployee)
			.exchange()
			.expectStatus()
			.isBadRequest();
	}

	private List<EmployeeDto> getEmployees() {
		
		return webTestClient
					.get()
					.uri(BASE_URI)
					.headers(headers -> headers.setBasicAuth(user, pass))
					.exchange()
					.expectStatus()
					.isOk()
					.expectBodyList(EmployeeDto.class)
					.returnResult()
					.getResponseBody();
	}
	
}
