package hu.webuni.hr.atold.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.reactive.server.WebTestClient;

import hu.webuni.hr.atold.dto.CompanyDto;
import hu.webuni.hr.atold.dto.EmployeeDto;
import hu.webuni.hr.atold.dto.LoginDto;
import hu.webuni.hr.atold.mapper.CompanyMapper;
import hu.webuni.hr.atold.mapper.EmployeeMapper;
import hu.webuni.hr.atold.model.Employee;
import hu.webuni.hr.atold.model.Position;
import hu.webuni.hr.atold.repository.EmployeeRepository;
import hu.webuni.hr.atold.repository.PositionRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
public class CompanyControllerIT {
	
	private static final String BASE_URI = "/api/companies";
	private static final String LOGIN = "/api/login";
	
	@Autowired
	WebTestClient webTestClient;
	
	@Autowired
	CompanyService companyService;
	
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	CompanyMapper companyMapper;
	
	@Autowired
	PositionRepository positionRepository;
	
	@Autowired
	EmployeeMapper employeeMapper;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	InitDbService initDb;
	
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
	void addEmployeeToCompany() throws Exception {
		
		CompanyDto comp = new CompanyDto("111111", "Teszt cég", "Teszt 1 utca", null);
		CompanyDto compSaved = saveCompany(comp);
		
		assertThat(compSaved.equals(comp));
		
		Position pos = new Position("Titkár", 100000);
		positionRepository.save(pos);
		
		EmployeeDto employee = new EmployeeDto(1L, "Teszt Miklós", pos, 111110, LocalDateTime.parse("2020-01-01T08:00:00"), null);
		saveEmployee(employee);
		
		addEmployee(compSaved, employee);
		CompanyDto compSaved2 = getCompany(compSaved);
		
		assertThat(compSaved2.getEmployeeList().contains(employee));
		
		initDb.clearDb();
	}
	
	@Test
	void overwriteEmployeeOfCompany() throws Exception {
		
		CompanyDto comp = new CompanyDto("111111", "Teszt cég", "Teszt 1 utca", null);
		CompanyDto compSaved = saveCompany(comp);
		
		assertThat(compSaved.equals(comp));
		
		Position pos = new Position("Titkár", 100000);
		positionRepository.save(pos);
		
		EmployeeDto employee = new EmployeeDto(1L, "Teszt Miklós", pos, 111110, LocalDateTime.parse("2020-01-01T08:00:00"), null);
		saveEmployee(employee);
		
		addEmployee(compSaved, employee);
		CompanyDto compSaved2 = getCompany(compSaved);
		
		assertThat(compSaved2.getEmployeeList().contains(employee));
		
		overtwriteEmployee(compSaved2, employee);
		compSaved2 = getCompany(compSaved);
		assertThat(compSaved2.getEmployeeList().contains(employee));
		
		initDb.clearDb();
		
	}
	
	@Test
	void deleteEmployeeFromCompany() throws Exception {
		
		CompanyDto comp = new CompanyDto("111111", "Teszt cég", "Teszt 1 utca", null);
		CompanyDto compSaved = saveCompany(comp);
		
		assertThat(compSaved.equals(comp));
		
		Position pos = new Position("Titkár", 100000);
		positionRepository.save(pos);
		
		EmployeeDto employee = new EmployeeDto(1L, "Teszt Miklós", pos, 111110, LocalDateTime.parse("2020-01-01T08:00:00"), null);
		saveEmployee(employee);
		
		addEmployee(compSaved, employee);
		CompanyDto compSaved2 = getCompany(compSaved);
		
		EmployeeDto savedEmp = compSaved2.getEmployeeList().get(0);
		
		compSaved2 = deleteEmployee(compSaved2, savedEmp);
		assertThat(compSaved2.getEmployeeList() == null);
	
		initDb.clearDb();
		
	}
	
	private void saveEmployee(EmployeeDto employee) {
		
		String token = getJwtToken();

		webTestClient
			.post()
			.uri("/api/employees")
			.headers(headers -> headers.setBearerAuth(token))
			.bodyValue(employee)
			.exchange()
			.expectStatus()
			.isOk();			
		
	}
	
	private CompanyDto deleteEmployee(CompanyDto company, EmployeeDto employee) {
		
		String token = getJwtToken();
		
		return webTestClient
					.delete()
					.uri(BASE_URI + "/"+company.getId()+"/employeeEdit/"+employee.getId())
					.headers(headers -> headers.setBearerAuth(token))
					.exchange()
					.expectStatus()
					.isOk()
					.expectBody(CompanyDto.class)
					.returnResult()
					.getResponseBody();
	}

	private CompanyDto getCompany(CompanyDto comp) {
		
		String token = getJwtToken();
		
		return webTestClient
					.get()
					.uri(BASE_URI + "/" + comp.getId())
					.headers(headers -> headers.setBearerAuth(token))
					.exchange()
					.expectStatus()
					.isOk()
					.expectBody(CompanyDto.class)
					.returnResult()
					.getResponseBody();
	}
	
	
	private CompanyDto saveCompany(CompanyDto comp) {
		
		String token = getJwtToken();
		
		return webTestClient
					.post()
					.uri(BASE_URI + "/0")
					.headers(headers -> headers.setBearerAuth(token))
					.bodyValue(comp)
					.exchange()
					.expectBody(CompanyDto.class)
					.returnResult()
					.getResponseBody();
					
	}
	
	private CompanyDto addEmployee(CompanyDto comp, EmployeeDto emp) {
		
		
		String token = getJwtToken();
		
		List<EmployeeDto> em = new ArrayList<EmployeeDto>();
		em.add(emp);
		
		return webTestClient
				.post()
				.uri(BASE_URI + "/" + comp.getId() + "/employeeEdit")
				.headers(headers -> headers.setBearerAuth(token))
				.bodyValue(em)
				.exchange()
				.expectBody(CompanyDto.class)
				.returnResult()
				.getResponseBody();
		
	}
	
	private CompanyDto overtwriteEmployee(CompanyDto comp, EmployeeDto emp) {
		
		String token = getJwtToken();
		
		List<EmployeeDto> em = new ArrayList<EmployeeDto>();
		em.add(emp);
		
		return webTestClient
				.put()
				.uri(BASE_URI + "/" + comp.getId() + "/employeeEdit")
				.headers(headers -> headers.setBearerAuth(token))
				.bodyValue(em)
				.exchange()
				.expectBody(CompanyDto.class)
				.returnResult()
				.getResponseBody();
		
	}
	
	String getJwtToken() {
		
		LoginDto login = new LoginDto("user", "pass");
		
		return webTestClient
				.post()
				.uri(LOGIN)
				.bodyValue(login)
				.exchange()
				.expectStatus()
				.isOk()
				.expectBody(String.class)
				.returnResult()
				.getResponseBody();
				
	}

}
