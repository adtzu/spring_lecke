package hu.webuni.hr.atold.web;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;

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
	public EmployeeDto getEmployeeById(@PathVariable long id) {
		
		Optional<Employee> emp = employeeServcie.getEmployee(id);
		if(emp.isPresent())
		{
			return employeeMapper.employeeToDto(emp.get());
		}
		else
		{
			return null;
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
	
	@GetMapping("/position/{position}")
	public Collection<EmployeeDto> getEmployeesByPosition(@PathVariable String position) {
		
		return employeeMapper.employeeDtoListToEmployeeList(employeeServcie.getEmployeesByPosition(position));
	}
	
	@GetMapping("/name/{name}")
	public Collection<EmployeeDto> getEmployeesByName(@PathVariable String name) {
		
		return employeeMapper.employeeDtoListToEmployeeList(employeeServcie.getEmployeesByName(name));
	}
	
	@GetMapping("/enterance/start/{start}/end/{end}")
	public Collection<EmployeeDto> getEmployeesByEnterance(@PathVariable LocalDateTime start, @PathVariable LocalDateTime end) {
		
		return employeeMapper.employeeDtoListToEmployeeList(employeeServcie.getEmployeesByEnteranceDate(start, end));
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
