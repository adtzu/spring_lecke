package hu.webuni.hr.atold.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Positive;

import org.springframework.format.annotation.DateTimeFormat;

public class EmployeeDto {
	
	private long id;
	
	@NotNull
	private String name;
	
	@NotNull
	private String position;
	
	@Positive
	private int salary;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	@Past
	private LocalDateTime enterance;
	
	public EmployeeDto(long id, String name, String position, int salary, LocalDateTime enterance) {
		this.id = id;
		this.name = name;
		this.position = position;
		this.salary = salary;
		this.enterance = enterance;
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

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
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
}
