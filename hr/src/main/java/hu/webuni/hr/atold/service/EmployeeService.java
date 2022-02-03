package hu.webuni.hr.atold.service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import hu.webuni.hr.atold.model.Employee;
import hu.webuni.hr.atold.repository.EmployeeRepository;

@Service
public abstract class EmployeeService {
	
	@Autowired
	EmployeeRepository employeRepository;
	
	
	public abstract int getPayRaisePercent(Employee employee);
	
	public Collection<Employee> getEmployeeList() {
		
		return employeRepository.findAll();
	}
	
	public Optional<Employee> getEmployee(long id) {
		
		return employeRepository.findById(id);
	}
	
	public List<Employee> salaryFiltered(int salary) {
		
		return employeRepository.findBySalaryGreaterThanEqual(salary);	
	}
	
	@Transactional
	public Employee addNewEmployee(Employee emp) {
		
		return employeRepository.save(emp);
	}
	
	@Transactional
	public void removeEmployee(long id) {
		
		employeRepository.deleteById(id);
	}
	
	public List<Employee> getEmployeesByPosition(String position) {
		
		return employeRepository.findByPosition(position);
	}
	
	public Page<Employee> getEmployeesByPosition(String position, Pageable page) {
		
		Page<Employee> p = employeRepository.findByPosition(position, page);
		return p;
	}
	
	public List<Employee> getEmployeesByName(String name) {
		
		return employeRepository.findByNameStartingWithIgnoreCase(name);
	}
	
	public List<Employee> getEmployeesByEnteranceDate(LocalDateTime start, LocalDateTime end) {
		
		return employeRepository.findByEnteranceBetween(start, end);
	}
	
	public List<Employee> findEmployeeByExample(Employee example) {
		
		LocalDateTime enteranceDate = null;
		LocalDateTime defTime = LocalDateTime.of(0001, 01, 01, 00, 00, 00);
		
		Long id 		= example.getId();
		String name 	= example.getName();
		String position = example.getPosition().getName();
		int salary 		= example.getSalary();
		 
		if(example.getEnterance() != null) {
			enteranceDate = example.getEnterance();
		}
		String companyName = !example.getCompany().getName().isEmpty() ? example.getCompany().getName() : "";

		Specification<Employee> specification = Specification.where(null);
		
		if(id > 0 && id != null) {
			specification = specification.and(EmployeeSpecification.hasId(id));
		}
		
		if(StringUtils.hasText(name) && !name.isEmpty()) {
			specification = specification.and(EmployeeSpecification.nameStartingWithIgnoreCase(name));
		}
		
		if(StringUtils.hasText(position) && !position.isEmpty()) {
			specification = specification.and(EmployeeSpecification.hasPosition(position));
		}
		
		if(salary > 0) {
			specification = specification.and(EmployeeSpecification.hasSalary(salary));
		}
		
		if(enteranceDate.isAfter(defTime)) {
			specification = specification.and(EmployeeSpecification.startWorkingDate(enteranceDate));
		}
		
		if(StringUtils.hasText(companyName) || !companyName.isEmpty()) {
			specification = specification.and(EmployeeSpecification.hasCompany(companyName));
		}
		
		return employeRepository.findAll(specification, Sort.by("id"));		
	}

}
