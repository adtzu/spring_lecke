package hu.webuni.hr.atold.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import hu.webuni.hr.atold.model.CompanyForm;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CompanyDto {
	
	private Long id;
	
	private String registrationNumber;
	private String name;
	private String address;
	private List<EmployeeDto> employeeList = new ArrayList<>();
	private CompanyForm companyForm;
	
	public CompanyDto(String registrationNumber, String name, String address, List<EmployeeDto> employeeList) {
		this.registrationNumber = registrationNumber;
		this.name = name;
		this.address = address;
		this.employeeList = employeeList;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<EmployeeDto> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(List<EmployeeDto> employeeList) {
		this.employeeList = employeeList;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CompanyForm getCompanyForm() {
		return companyForm;
	}

	public void setCompanyForm(CompanyForm companyForm) {
		this.companyForm = companyForm;
	}
}
