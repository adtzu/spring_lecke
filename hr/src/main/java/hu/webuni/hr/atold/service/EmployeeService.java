package hu.webuni.hr.atold.service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springdoc.core.converters.models.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

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
	
	public Employee addNewEmployee(Employee emp) {
		
		return employeRepository.save(emp);
	}
	
	public void removeEmployee(long id) {
		
		employeRepository.deleteById(id);
	}
	
	public List<Employee> getEmployeesByPosition(String position) {
		
		return employeRepository.findByPosition(position);
	}
	
	/*public Page<Employee> getEmployeesByPosition(String position, Pageable page) {
		
		Page<Employee> p = employeRepository.findByPosition(position, page);
		return p;
	}*/
	
	public List<Employee> getEmployeesByName(String name) {
		
		return employeRepository.findByNameStartingWithIgnoreCase(name);
	}
	
	public List<Employee> getEmployeesByEnteranceDate(LocalDateTime start, LocalDateTime end) {
		
		return employeRepository.findByEnteranceBetween(start, end);
	}

}
