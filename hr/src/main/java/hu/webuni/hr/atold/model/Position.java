package hu.webuni.hr.atold.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Position {
	
	@Id
	@GeneratedValue
	private long id;
	
	private String name;
	private enum education {
		
		érettségi,
		főiskola,
		egyetem,
		phd		
	}
	
	private int minVage;

	
	public Position() {
		
	}
	
	public Position(String name, int minVage) {

		this.name = name;
		this.minVage = minVage;
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

	public int getMinVage() {
		return minVage;
	}

	public void setMinVage(int minVage) {
		this.minVage = minVage;
	}

}
