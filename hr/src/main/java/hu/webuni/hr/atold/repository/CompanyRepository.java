package hu.webuni.hr.atold.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import hu.webuni.hr.atold.model.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
	
	
	@Query("SELECT DISTINCT c FROM Company c JOIN c.employeeList e WHERE e.salary >= :salaryLimit")
	Collection<Company> findBySalaryGreaterThanEqual(int salaryLimit);
	
	
	@Query("SELECT DISTINCT c FROM Company c WHERE SIZE(c.employeeList) > :employeeLimit")
	Collection<Company> findByEmployeeCountGreaterThan(int employeeLimit);
	
	
	@Query("SELECT e.position, AVG(e.salary) FROM Company c JOIN c.employeeList e WHERE c.id = :companyId GROUP BY e.position ORDER BY AVG(e.salary) DESC")
	Collection<Company> averageSalaryByPosition(int companyId);
	
	
	@EntityGraph(attributePaths = {"employeeList", "employeeList.position"})
	@Query("SELECT c FROM Company c")
	Collection<Company> findAllWithEmployees();
	
	
	@EntityGraph(attributePaths = {"employeeList", "employeeList.position"})
	@Query("SELECT c FROM Company c WHERE c.id = :id")
	Company findOneWithEmployees(Long id);
	

}
