package hu.webuni.hr.atold.service;
import org.springframework.stereotype.Service;

import hu.webuni.hr.atold.model.Employee;

@Service
public class SalaryService {
	
	private EmployeeService employeeService;
	
	public SalaryService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	public void setEmployeePayRaise(Employee employee) {
		employee.setSalary((int)(employee.getSalary() / 100.0 * (100 + employeeService.getPayRaisePercent(employee))));
	}
	
	public int getEmployeePayraisePercent(Employee employee) {
		
		return employeeService.getPayRaisePercent(employee);
	}
	
}
