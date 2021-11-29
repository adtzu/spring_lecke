package hu.webuni.hr.atold;

import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hu.webuni.hr.atold.model.Employee;
import hu.webuni.hr.atold.service.DefaultEmployeeService;
import hu.webuni.hr.atold.service.EmployeeService;
import hu.webuni.hr.atold.service.SalaryService;

@SpringBootApplication
public class HrApplication implements CommandLineRunner{
	
	@Autowired
	SalaryService salaryService;

	public static void main(String[] args) {
		SpringApplication.run(HrApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Employee emp1 = new Employee(1, "Teszt Elek", "atyaúristen", 1000, LocalDateTime.parse("2021-11-29T08:00:00"));
		salaryService.setEmployeePayRaise(emp1);
		System.out.println(emp1.getSalary());
		
		Employee emp2 = new Employee(1, "Kandisz Nóra", "atyaúristen helyettes", 1000, LocalDateTime.parse("2011-11-28T08:00:00"));
		salaryService.setEmployeePayRaise(emp2);
		System.out.println(emp2.getSalary());
		
	}
}
