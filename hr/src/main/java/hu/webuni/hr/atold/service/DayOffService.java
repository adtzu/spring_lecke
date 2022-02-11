package hu.webuni.hr.atold.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.webuni.hr.atold.model.DayOff;
import hu.webuni.hr.atold.model.Employee;
import hu.webuni.hr.atold.repository.DayOffRepository;
import hu.webuni.hr.atold.repository.EmployeeRepository;

@Service
public class DayOffService {

	
	@Autowired
	DayOffRepository dayOffRepository;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Transactional
	public Page<DayOff> listAllPage(Pageable page) {
		
		return dayOffRepository.findAllPage(page);
	}
	
	public List<DayOff> listAll() {
		
		return dayOffRepository.findAll();
	}
	
	@Transactional
	public DayOff addNew(Long employeeId, DayOff dayOff) {
		
		Employee emp = employeeRepository.getById(employeeId);
		dayOff.setApplicant(emp);
		dayOffRepository.save(dayOff);
		
		return dayOff;
	}
	
	@Transactional
	public void delete(Long id) {
		
		DayOff toDelete = dayOffRepository.getById(id);
		if(toDelete.getStatus().equals("pending"))
		{
			dayOffRepository.deleteById(id);
		}		
	}
	
	@Transactional
	public DayOff approveDayOff(Long id, Long approverId) {
		
		DayOff toApprove = dayOffRepository.getById(id);
		Employee approver = employeeRepository.getById(approverId);
		
		if(toApprove.getApplicant().getManager().getId() == approver.getId())
		{
			throw new AccessDeniedException("Not the ,anager of this user");
		}
		
		toApprove.setStatus("approved");
		toApprove.setApprover(approver);
		dayOffRepository.save(toApprove);
		
		return toApprove;
	}
	
	public List<DayOff> findDayOff(DayOff dayOff) {
		
		Specification<DayOff> specification = Specification.where(null);
		
		if(dayOff.getId() != null) 
		{
			specification = specification.and(DayOffSpecification.searchById(dayOff.getId()));
		}
		
		if(dayOff.getStatus() != null) 
		{
			specification = specification.and(DayOffSpecification.searchByStatus(dayOff.getStatus()));
		}
		
		if(dayOff.getApplicant() != null) 
		{
			specification = specification.and(DayOffSpecification.searchByName(dayOff.getApplicant().getName()));
		}
		
		if(dayOff.getApprover() != null) 
		{
			specification = specification.and(DayOffSpecification.searchByApprover(dayOff.getApprover().getName()));
		}
		
		if(dayOff.getStartDate() != null) 
		{
			specification = specification.and(DayOffSpecification.searchByStartDate(dayOff.getStartDate(), dayOff.getToDate()))
					.and(DayOffSpecification.searchByEndDate(dayOff.getStartDate(), dayOff.getToDate()));
		}
			
		return dayOffRepository.findAll(specification, Sort.by("id"));
		
	}

}
