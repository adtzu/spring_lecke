package hu.webuni.hr.atold.service;

import java.time.LocalDateTime;

import org.springframework.data.jpa.domain.Specification;

import hu.webuni.hr.atold.model.Company_;
import hu.webuni.hr.atold.model.Employee;
import hu.webuni.hr.atold.model.Employee_;
import hu.webuni.hr.atold.model.Position_;

public class EmployeeSpecification {
	
	public static Specification<Employee> hasId(Long id) {
		return (root, cq, cb) -> cb.equal(root.get(Employee_.id), id);
	}
	
	public static Specification<Employee> nameStartingWithIgnoreCase(String name) {
		return (root, cq, cb) -> cb.like(cb.lower(root.get(Employee_.name)), name.toLowerCase() + "%");
	}

	public static Specification<Employee> hasPosition(String position) {
		return (root, cq, cb) -> cb.equal(root.get(Employee_.position).get(Position_.name), position);
	}

	public static Specification<Employee> hasSalary(int salary) {
		int salaryPlus5percent = salary + (salary/100)*5;
		int salaryMinus5percent = salary - (salary/100)*5;

		return (root, cq, cb) -> cb.between(root.get(Employee_.salary), salaryMinus5percent, salaryPlus5percent);
	}

	public static Specification<Employee> startWorkingDate(LocalDateTime enteranceDate) {
		return (root, cq, cb) -> cb.equal(root.get(Employee_.enterance), enteranceDate);
	}

	public static Specification<Employee> hasCompany(String company) {
		return (root, cq, cb) -> cb.like(cb.lower(root.get(Employee_.company).get(Company_.name)), company.toLowerCase() + "%");
	}

}
