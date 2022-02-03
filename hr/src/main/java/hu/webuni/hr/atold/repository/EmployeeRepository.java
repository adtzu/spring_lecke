package hu.webuni.hr.atold.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import hu.webuni.hr.atold.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>, JpaSpecificationExecutor<Employee> {
	
	List<Employee> findBySalaryGreaterThanEqual(int salary);
	
	List<Employee> findByPosition(String position);
	
	Page<Employee> findByPosition(String position, Pageable page);
	
	List<Employee> findByNameStartingWithIgnoreCase(String name);
	
	List<Employee> findByEnteranceBetween(LocalDateTime start, LocalDateTime end);

}
