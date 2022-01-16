package hu.webuni.hr.atold.repository;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import hu.webuni.hr.atold.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
	List<Employee> findBySalaryGreaterThanEqual(int salary);
	
	List<Employee> findByPosition(String position);
	
	List<Employee> findByNameStartingWithIgnoreCase(String name);
	
	List<Employee> findByEnteranceBetween(LocalDateTime start, LocalDateTime end);

}
