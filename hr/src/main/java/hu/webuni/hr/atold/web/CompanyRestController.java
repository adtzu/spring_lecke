package hu.webuni.hr.atold.web;

import java.util.Collection;
import java.util.List;
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

import hu.webuni.hr.atold.dto.CompanyDto;
import hu.webuni.hr.atold.dto.EmployeeDto;
import hu.webuni.hr.atold.mapper.CompanyMapper;
import hu.webuni.hr.atold.mapper.EmployeeMapper;
import hu.webuni.hr.atold.service.CompanyService;

@RestController
@RequestMapping("/api/companies")
public class CompanyRestController {
	
	@Autowired
	CompanyService companyServcie;
	
	@Autowired
	CompanyMapper companyMapper;
	
	@Autowired
	EmployeeMapper employeeMapper;
	
	
	@GetMapping
	public Collection<CompanyDto> getAllCompanies(@RequestParam(defaultValue = "false") String full) {
		
		if(full.equals("true"))
		{
			return companyMapper.companiesToCompaniesDto(companyServcie.getAllCompanies()); 
		}
		else
		{
			return companyMapper.companiesToCompaniesDtoWithoutEmployees(companyServcie.getAllCompaniesWithoutEmployees());
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CompanyDto> getCompanyById(@PathVariable long id) {
		
		CompanyDto company = companyMapper.companyToDto(companyServcie.getCompanyById(id));
		if(company.equals(null))
		{
			return ResponseEntity.notFound().build();
		}
		else
		{
			return ResponseEntity.ok(company);
		}
	}
	
	@GetMapping("/hasHihgerSalary")
	public Collection<CompanyDto> employeeHasHigherSalaryThan(@RequestParam(defaultValue = "0") int salary) {
		
		return companyMapper.companiesToCompaniesDtoWithoutEmployees(companyServcie.employeeHasHigherSalaryThan(salary));
	}
	
	@GetMapping("/hasMoreEmployeeThan")
	public Collection<CompanyDto> employeeCountGreaterThan(@RequestParam(defaultValue = "0") int limit) {
		
		return companyMapper.companiesToCompaniesDtoWithoutEmployees(companyServcie.employeeCountGreaterThan(limit));
	}
	
	@GetMapping("/averageSalaryPerPosition")
	public Collection<CompanyDto> averageSalaryPerPosition(@RequestParam(defaultValue = "0") int companyId) {
		
		return companyMapper.companiesToCompaniesDto(companyServcie.averageSalaryPerPosition(companyId));
	}
	
	
	
	@PutMapping("/{id}")
	public ResponseEntity<CompanyDto> modifyCompany(@RequestBody CompanyDto companyDto, @PathVariable Long id) {
		
		CompanyDto company = companyMapper.companyToDto(companyServcie.overwriteCompany(id, companyMapper.dtoToCompany(companyDto)));
		
		if(company.equals(null))
		{
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(company);		
	}
	
	@PostMapping("/{id}")
	public CompanyDto createCompany(@RequestBody CompanyDto companyDto, @PathVariable Long id) {
		
		return companyMapper.companyToDto(companyServcie.saveCompany(id, companyMapper.dtoToCompany(companyDto)));
	}
	
	@DeleteMapping("/{id}")
	public void deleteCompany(@PathVariable Long id) {
		
		companyServcie.deleteCompany(id);
	
	}
	
	@PutMapping("/{id}/employeeEdit")
	public ResponseEntity<CompanyDto> addNewEmployee(@RequestBody EmployeeDto employeeDto, @PathVariable Long id) {
		
		return ResponseEntity.ok(
				companyMapper.companyToDto(
				companyServcie.editEmployee(id, employeeMapper.dtoToEmployee(employeeDto))));
		
	}
	
	@DeleteMapping("/{id}/employeeEdit/{employeeId}")
	public ResponseEntity<CompanyDto> deleteEmployee(@PathVariable Long employeeId, @PathVariable Long id) {
		
		CompanyDto comp = companyMapper.companyToDto(companyServcie.removeEmployee(id, employeeId)); 
		return ResponseEntity.ok(comp);
	}
	
	@PostMapping("/{id}/employeeEdit")
	public ResponseEntity<CompanyDto> updateEmployees(@RequestBody List<EmployeeDto> employeeList, @PathVariable Long id) {
		
		CompanyDto company = companyMapper.companyToDto(companyServcie.editEmployeeList(employeeMapper.employeeDtoListToEmployeeList(employeeList), id));
		
		if(company.equals(null))
		{
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(company);	
	}	
}