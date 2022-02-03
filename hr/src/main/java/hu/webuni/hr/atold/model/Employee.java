package hu.webuni.hr.atold.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Employee {
	
	@Id
	@GeneratedValue
	private long id;
	
	private String name;
	
	@ManyToOne
	private Position position;
	private int salary;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime enterance;
	
	@ManyToOne
	private Company company;
	
	
	public Employee() {
		
	}

	public Employee(String name, int salary, LocalDateTime enterance, Company company) {

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
	
	
	
}
