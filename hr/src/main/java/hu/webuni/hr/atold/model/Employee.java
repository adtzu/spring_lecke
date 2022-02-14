package hu.webuni.hr.atold.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Positive;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Employee {
	
	@Id
	@GeneratedValue
	private long id;
	
	@NotEmpty
	private String name;
	
	private String username;
	private String password;
	
	@ManyToOne
	private Position position;
	
	@Positive
	private int salary;
	
	@Past
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime enterance;
	
	@ManyToOne
	private Company company;
	
	@ManyToOne
	private Employee manager;
	
	@OneToMany(mappedBy = "manager")
	private List<Employee> managedEmployees;
	
	
	public Employee() {
		
	}

	public Employee(@NotEmpty String name, @Positive int salary, @Past LocalDateTime enterance, Company company) {

		this.name = name;
		this.salary = salary;
		this.enterance = enterance;
		this.company = company;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Position getPosition() {
		return position;
	}
	public void setPosition(Position position) {
		this.position = position;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public LocalDateTime getEnterance() {
		return enterance;
	}
	public void setEnterance(LocalDateTime enterance) {
		this.enterance = enterance;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Employee getManager() {
		return manager;
	}

	public void setManager(Employee manager) {
		this.manager = manager;
	}
	
	public List<Employee> getManagedEmployees() {
		return managedEmployees;
	}

	public void setManagedEmployees(List<Employee> managedEmployees) {
		this.managedEmployees = managedEmployees;
	}
	
}
