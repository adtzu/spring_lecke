package hu.webuni.hr.atold.web;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hu.webuni.hr.atold.dto.EmployeeDto;

@RestController
@RequestMapping("/api/employees")
public class EmployeeRestController {
	
	private Map<Long, EmployeeDto> employees = new HashMap<>();
	
	{
		employees.put(1L, new EmployeeDto(1, "Teszt Elek", "CEO", 900000, LocalDateTime.parse("2010-01-02T08:00")));
		employees.put(2L, new EmployeeDto(2, "Kandisz Nóra", "Titkárnő", 250000, LocalDateTime.parse("2010-03-16T08:00")));
	}
	
	@GetMapping
	public Collection<EmployeeDto> getAll() {
		
		return employees.values();
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable long id) {
		
		if(employees.containsKey(id))
		{
			return ResponseEntity.ok(employees.get(id));
		}
		else
		{
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/salaryFilter")
	public ResponseEntity<Collection<EmployeeDto>> getEmployeesBySalary(@RequestParam(name="salary") int salary) {
		
		Collection<EmployeeDto> emp = employees.entrySet().stream().filter(a -> a.getValue().getSalary() > salary).collect(Collectors.toMap(a -> a.getKey(), a -> a.getValue())).values();
		
		if(emp.isEmpty())
		{
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(emp);
	}
	
	
	@PostMapping
	public EmployeeDto createEmployee(@RequestBody EmployeeDto employeeDto) {
		
		employees.put(employeeDto.getId(), employeeDto);
		return employeeDto;
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<EmployeeDto> modifyEmployee(@RequestBody EmployeeDto employeeDto, @PathVariable Long id) {
		
		if(employees.containsKey(id))
		{
			return ResponseEntity.notFound().build();
		}
		
		employeeDto.setId(id);
		employees.put(id, employeeDto);
		
		return ResponseEntity.ok(employeeDto);
		
	}
	
	@DeleteMapping("/{id}")
	public void deleteEmployee(@PathVariable Long id) {
		
		employees.remove(id);
		
	}
	

}