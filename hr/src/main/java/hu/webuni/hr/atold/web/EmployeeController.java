package hu.webuni.hr.atold.web;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import hu.webuni.hr.atold.model.Employee;

@Controller
public class EmployeeController {
	
	private List<Employee> employeesList = new ArrayList<>();
	
	{
		employeesList.add(new Employee(0, "Teszt Elek", "vezérigazgató", 700000, LocalDateTime.parse("2011-02-10T09:00:00")));
	}
	
	@GetMapping("/")
	public String home() {
		return "index";
	}
	
	@GetMapping("/employees")
	public String listEmployees(Map<String, Object> model) {
		model.put("employees", employeesList);
		model.put("newEmployee", new Employee());
		
		return "employees";
	}
	
	@PostMapping("/employees")
	public String addNewEmployee(Employee newEmployee) {
		
		long newId = (long)employeesList.size();
		newEmployee.setId(newId);
		
		employeesList.add(newEmployee);
		return "redirect:employees";
		
	}

}
