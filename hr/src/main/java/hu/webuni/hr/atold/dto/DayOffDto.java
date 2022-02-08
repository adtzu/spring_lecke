package hu.webuni.hr.atold.dto;

import java.time.LocalDate;

import hu.webuni.hr.atold.model.Employee;

public class DayOffDto {
	
	Long id;
	Employee applicant;
	String status;
	LocalDate startDate;
	LocalDate toDate;
	Employee approver;
	
	public DayOffDto() {
		
	}

	public DayOffDto(Employee applicant, String status, LocalDate startDate, LocalDate toDate, Employee approver) {

		this.applicant = applicant;
		this.status = status;
		this.startDate = startDate;
		this.toDate = toDate;
		this.approver = approver;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Employee getApplicant() {
		return applicant;
	}

	public void setApplicant(Employee applicant) {
		this.applicant = applicant;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getToDate() {
		return toDate;
	}

	public void setToDate(LocalDate toDate) {
		this.toDate = toDate;
	}

	public Employee getApprover() {
		return approver;
	}

	public void setApprover(Employee approver) {
		this.approver = approver;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}	

}
