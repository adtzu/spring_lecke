package hu.webuni.hr.atold.service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import hu.webuni.hr.atold.dto.EmployeeDto;
import hu.webuni.hr.atold.model.Employee;

public abstract class EmployeeService {
	
private Map<Long, Employee> employees = new HashMap<>();
	
	{
		employees.put(1L, new Employee(1, "Teszt Elek", "CEO", 900000, LocalDateTime.parse("2010-01-02T08:00")));
		employees.put(2L, new Employee(2, "Kandisz Nóra", "Titkárnő", 250000, LocalDateTime.parse("2010-03-16T08:00")));
	}
	
	public abstract int getPayRaisePercent(Employee employee);
	
	public Collection<Employee> getEmployeeList() {
		
		return employees.values(); 
	}

}
