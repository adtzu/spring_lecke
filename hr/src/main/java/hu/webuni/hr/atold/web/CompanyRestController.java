package hu.webuni.hr.atold.web;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
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

import hu.webuni.hr.atold.dto.CompanyDto;
import hu.webuni.hr.atold.dto.EmployeeDto;

@RestController
@RequestMapping("/api/companies")
public class CompanyRestController {
	
	private Map<Long, CompanyDto> companies = new HashMap<>();
	
	@GetMapping
	public Collection<CompanyDto> getAllCompanies(@RequestParam(defaultValue = "false") String full) {
		
		if(full.equals("true"))
		{
			return companies.values();
		}
		else
		{
			Map<Long, CompanyDto> companies2 = companies.entrySet().stream().collect(Collectors.toMap(k -> (Long)k.getKey(), v -> {v.getValue().setEmployeeList(null); return (CompanyDto)v;}));
			return companies2.values();
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CompanyDto> getCompanyById(@PathVariable long id) {
		
		if(companies.containsKey(id))
		{
			return ResponseEntity.ok(companies.get(id));
		}
		else
		{
			return ResponseEntity.notFound().build();
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CompanyDto> modifyCompany(@RequestBody CompanyDto CompanyDto, @PathVariable Long id) {
		
		if(companies.containsKey(id))
		{
			return ResponseEntity.notFound().build();
		}

		companies.put(id, CompanyDto);
		
		return ResponseEntity.ok(CompanyDto);		
	}
	
	@PostMapping("/{id}")
	public CompanyDto createCompany(@RequestBody CompanyDto companyDto, @PathVariable Long id) {
		
		companies.put(id, companyDto);
		return companyDto;
	}
	
	@DeleteMapping("/{id}")
	public void deleteCompany(@PathVariable Long id) {
		
		companies.remove(id);
	}
	
	@PutMapping("/employeeEdit/{id}")
	public ResponseEntity<CompanyDto> addNewEmployee(@RequestBody EmployeeDto employeeDto, @PathVariable Long id) {
		
		if(!companies.containsKey(id))
		{
			return ResponseEntity.notFound().build();
		}
		
		List<EmployeeDto> currentEmployees = companies.get(id).getEmployeeList();
		currentEmployees.add(employeeDto);
		companies.get(id).setEmployeeList(currentEmployees);
		
		return ResponseEntity.ok(companies.get(id));		
	}
	
	@DeleteMapping("/employeeEdit/{id}/{employeeId}")
	public ResponseEntity<CompanyDto> deleteEmployee(@PathVariable Long employeeId, @PathVariable Long id) {
		
		if(!companies.containsKey(id))
		{
			return ResponseEntity.notFound().build();
		}
		
		List<EmployeeDto> currentEmployees = companies.get(id).getEmployeeList();
		currentEmployees.remove(employeeId);
		companies.get(id).setEmployeeList(currentEmployees);
		
		return ResponseEntity.ok(companies.get(id));		
	}
	
	@PostMapping("/employeeEdit/{id}")
	public ResponseEntity<CompanyDto> updateEmployees(@RequestBody List<EmployeeDto> employeeDto, @PathVariable Long id) {
		
		if(!companies.containsKey(id))
		{
			return ResponseEntity.notFound().build();
		}
		
		companies.get(id).setEmployeeList(employeeDto);
		
		return ResponseEntity.ok(companies.get(id));		
	}
	
	
	

	
}
