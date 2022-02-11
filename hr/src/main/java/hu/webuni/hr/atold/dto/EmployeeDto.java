package hu.webuni.hr.atold.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Positive;

import org.springframework.format.annotation.DateTimeFormat;

import hu.webuni.hr.atold.model.Position;

public class EmployeeDto {
	
	private long id;
	
	@NotNull
	private String name;
	
	@Positive
	private int salary;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	@Past
	private LocalDateTime enterance;
	
	private CompanyDto company;
	
	
	private Position position;
	
	public EmployeeDto() {
		
	}

	
	public EmployeeDto(long id, @NotEmpty String name, @NotEmpty Position position, @Positive int salary,
			@Past LocalDateTime enterance, CompanyDto company) {
		super();
		this.id = id;
		this.name = name;
		this.position = position;
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

	public CompanyDto getCompany() {
		return company;
	}

	public void setCompany(CompanyDto company) {
		this.company = company;
	}
}
