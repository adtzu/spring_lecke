package hu.webuni.hr.atold.model;

import java.time.LocalDateTime;

public class Employee {
	
	private long id;
	private String name;
	private String position;
	private int salary;
	private LocalDateTime enterance;
	
	
	public Employee(long id, String name, String position, int salary, LocalDateTime enterance) {

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
