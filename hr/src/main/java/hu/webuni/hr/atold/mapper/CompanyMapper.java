package hu.webuni.hr.atold.mapper;

import java.util.Collection;
import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import hu.webuni.hr.atold.dto.CompanyDto;
import hu.webuni.hr.atold.dto.EmployeeDto;
import hu.webuni.hr.atold.model.Company;
import hu.webuni.hr.atold.model.Employee;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
	
	Collection<CompanyDto> companiesToCompaniesDto(Collection<Company> companies);
	
	@IterableMapping(qualifiedByName = "summary")
	Collection<CompanyDto> companiesToCompaniesDtoWithoutEmployees(Collection<Company> companies);
	
	CompanyDto companyToDto(Company company);
	
	Company dtoToCompany(CompanyDto company);
	
	@Mapping(target="employeeList", ignore = true)
	@Named("summary")
	CompanyDto companyToDtoSummary(Company company);
	
	@Mapping(target = "company", ignore = true)
	EmployeeDto employeeToDto(Employee employee);
	
	Employee dtoToEmployee(EmployeeDto employee);
	
	List<Employee> employeeDtoListToEmployeeList(List<EmployeeDto> employeeDto);
	
	Collection<EmployeeDto> employeeDtoListToEmployeeList(Collection<Employee> employeeDto);

}
