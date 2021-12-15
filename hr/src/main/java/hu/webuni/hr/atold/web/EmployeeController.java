package hu.webuni.hr.atold.web;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import hu.webuni.hr.atold.dto.EmployeeDto;
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
	
	@GetMapping("/employees/delete/{id}")
	public String deleteEmployee(@PathVariable int id) {
		
		employeesList.remove(id);
		return "redirect:/employees";
		
	}
	
	@GetMapping("/employees/edit/{id}")
	public String editExistingEmployee(Map<String, Object> model, @PathVariable int id ) {
		
		model.put("employees", employeesList.get(id) );
		model.put("newEmployee", new Employee());
		return "employeeEditor";
	}
	
	@PostMapping("/employees/edit")
	public String modifyEmployee(Employee employee) {
		
		employeesList.remove((int)employee.getId());
		employeesList.add((int)employee.getId(), employee);
		
		return "redirect:/employees";
		
	}

}
