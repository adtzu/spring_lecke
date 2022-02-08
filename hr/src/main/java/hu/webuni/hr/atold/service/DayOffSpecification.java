package hu.webuni.hr.atold.service;

import java.time.LocalDate;

import org.springframework.data.jpa.domain.Specification;

import hu.webuni.hr.atold.model.DayOff;
import hu.webuni.hr.atold.model.DayOff_;

public class DayOffSpecification {

	public static Specification<DayOff> searchById(Long id) {
		
		return (root, cq, cb) -> cb.equal(root.get(DayOff_.id), id);
	}

	public static Specification<DayOff> searchByStatus(String status) {
		
		return (root, cq, cb) -> cb.equal(root.get(DayOff_.status), status);
	}

	public static Specification<DayOff> searchByName(String name) {
		
		return (root, cq, cb) -> cb.equal(root.get(DayOff_.applicant.getName()), name);
	}

	public static Specification<DayOff> searchByApprover(String name) {
		
		return (root, cq, cb) -> cb.equal(root.get(DayOff_.approver.getName()), name);
	}
	
	public static Specification<DayOff> searchByStartDate(LocalDate startDate, LocalDate toDate) {
		
		return (root, cq, cb) -> cb.between(root.get(DayOff_.startDate), startDate, toDate);
	}

	public static Specification<DayOff> searchByEndDate(LocalDate startDate, LocalDate toDate) {

		return (root, cq, cb) -> cb.between(root.get(DayOff_.toDate), startDate, toDate);
	}
	
	

}
