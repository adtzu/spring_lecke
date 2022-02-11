package hu.webuni.hr.atold.service;

import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import hu.webuni.hr.atold.model.Company;
import hu.webuni.hr.atold.model.CompanyForm;
import hu.webuni.hr.atold.model.Employee;
import hu.webuni.hr.atold.model.Position;
import hu.webuni.hr.atold.repository.CompanyFormRepository;
import hu.webuni.hr.atold.repository.CompanyRepository;
import hu.webuni.hr.atold.repository.EmployeeRepository;
import hu.webuni.hr.atold.repository.PositionRepository;

@Service
public class InitDbService {
	
	@Autowired
	CompanyRepository companyRepository;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	CompanyFormRepository companyFormRepository;
	
	@Autowired
	PositionRepository positionRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	
	public void clearDb( ) {
		
		employeeRepository.deleteAll();
		companyRepository.deleteAll();
		positionRepository.deleteAll();
	}
	
	public void insertTestData() {
		
		CompanyForm form = new CompanyForm(0, "Korlátolt felelősségű társaság", "Kft.");
		companyFormRepository.save(form);
		
		Position position = new Position("CEO", 500000);
		positionRepository.save(position);
		
		Company comp = new Company("1234qwe", "Tesztelek és társa Kft.", "Teszt város teszt utca 1/b", null);
		Employee emp = new Employee("Teszt Elek", 1000, LocalDateTime.parse("2021-11-29T08:00:00"), null);
		emp.setUsername("user");
		emp.setPassword(passwordEncoder.encode("user"));
		emp.setPosition(position);
		
		comp.addEmployee(emp);
		comp.setCompanyForm(form);
		
		companyRepository.save(comp);
		employeeRepository.save(emp);
				
	}
}
