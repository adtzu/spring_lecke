package hu.webuni.hr.atold.service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import hu.webuni.hr.atold.model.Employee;

@Service
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
	
	public Employee getEmployee(long id) {
		
		if(employees.containsKey(id))
		{
			return employees.get(id); 
		}
		else
		{
			return null;
		}
	}
	
	public Collection<Employee> salaryFiltered(int salary) {
		
		Collection<Employee> emp = employees.entrySet().stream().filter(a -> a.getValue().getSalary() > salary).collect(Collectors.toMap(a -> a.getKey(), a -> a.getValue())).values();
		return emp;		
	}
	
	public Employee addNewEmployee(Employee emp) {
		
		employees.put(emp.getId(), emp);
		return emp;
	}
	
	public void removeEmployee(long id) {
		
		employees.remove(id);
	}

}
