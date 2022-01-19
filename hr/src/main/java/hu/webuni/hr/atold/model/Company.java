package hu.webuni.hr.atold.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Company {
	
	@Id
	@GeneratedValue
	private long id;
	
	private String registrationNumber;
	private String name;
	private String address;
	
	@OneToMany(mappedBy = "company")
	private List<Employee> employeeList;
	
	@ManyToOne
	private CompanyForm companyForm;
	
	public Company() {
		
	}

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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}	
	
	public void addEmployee(Employee employee) {
		
		employee.setCompany(this);
	}

	public CompanyForm getCompanyForm() {
		return companyForm;
	}

	public void setCompanyForm(CompanyForm companyForm) {
		this.companyForm = companyForm;
	}
}
