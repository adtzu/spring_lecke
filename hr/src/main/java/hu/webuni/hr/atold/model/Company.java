package hu.webuni.hr.atold.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonInclude;

import hu.webuni.hr.atold.dto.EmployeeDto;

@Service
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Company {
	
	private String registrationNumber;
	private String name;
	private String address;
	private List<Employee> employeeList = new ArrayList<>();
	
	public Company(String registrationNumber, String name, String address, List<Employee> employeeList) {
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

	public List<Employee> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(List<Employee> employeeList) {
		this.employeeList = employeeList;
	}	
}
