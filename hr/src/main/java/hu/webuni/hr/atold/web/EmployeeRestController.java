package hu.webuni.hr.atold.web;

import java.util.Collection;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import hu.webuni.hr.atold.mapper.EmployeeMapper;
import hu.webuni.hr.atold.model.Employee;
import hu.webuni.hr.atold.service.EmployeeService;
import hu.webuni.hr.atold.service.SalaryService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeRestController {
	
	@Autowired
	SalaryService salaryService;
	
	@Autowired
	EmployeeMapper employeeMapper;
	
	@Autowired
	EmployeeService employeeServcie;
	
	
	@GetMapping
	public Collection<EmployeeDto> getAll() {
		
		return employeeMapper.employeeDtoListToEmployeeList(employeeServcie.getEmployeeList());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable long id) {
		
		EmployeeDto employee = employeeMapper.employeeToDto(employeeServcie.getEmployee(id));
		
		if(employee.equals(null))
		{
			return ResponseEntity.notFound().build();
		}
		else
		{
			return ResponseEntity.ok(employee);
		}
	}
	
	@GetMapping("/salaryFilter")
	public ResponseEntity<Collection<EmployeeDto>> getEmployeesBySalary(@RequestParam(name="salary") int salary) {
		
		Collection<EmployeeDto> emp = employeeMapper.employeeDtoListToEmployeeList(employeeServcie.salaryFiltered(salary));
		
		if(emp.isEmpty())
		{
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(emp);
	}
	
	
	@PostMapping
	public EmployeeDto createEmployee(@RequestBody @Valid EmployeeDto employeeDto) {
		
		return employeeMapper.employeeToDto(employeeServcie.addNewEmployee(employeeMapper.dtoToEmployee(employeeDto)));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<EmployeeDto> modifyEmployee(@RequestBody @Valid EmployeeDto employeeDto, @PathVariable Long id) {
		
		employeeDto.setId(id);
		EmployeeDto employee = employeeMapper.employeeToDto(employeeServcie.addNewEmployee(employeeMapper.dtoToEmployee(employeeDto)));
		
		if(employee.equals(null))
		{
			return ResponseEntity.notFound().build();
		}
		else
		{
			return ResponseEntity.ok(employee);
		}
	}
	
	@DeleteMapping("/{id}")
	public void deleteEmployee(@PathVariable Long id) {
		
		employeeServcie.removeEmployee(id);		
	}
	
	@GetMapping("/payrisePercent")
	public ResponseEntity<Integer> getSalaryIncreasePercent(@RequestBody Employee employee) {
		
		return ResponseEntity.ok(salaryService.getEmployeePayraisePercent(employee));	
	}
	

}
