package hu.webuni.hr.atold.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class CompanyForm {

	@Id
	@GeneratedValue
	private long id;
	
	private String longName;
	private String shortName;
	
	public CompanyForm() {
		
	}
	
	public CompanyForm(long id, String longName, String shortName) {
		this.id = id;
		this.longName = longName;
		this.shortName = shortName;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLongName() {
		return longName;
	}

	public void setLongName(String longName) {
		this.longName = longName;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	
}
