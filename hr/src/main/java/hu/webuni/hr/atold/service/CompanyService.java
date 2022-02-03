package hu.webuni.hr.atold.service;

import java.util.Collection;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.webuni.hr.atold.mapper.CompanyMapper;
import hu.webuni.hr.atold.model.Company;
import hu.webuni.hr.atold.model.Employee;
import hu.webuni.hr.atold.repository.CompanyRepository;
import hu.webuni.hr.atold.repository.EmployeeRepository;

@Service
public class CompanyService {
	
	@Autowired
	CompanyRepository companyRepository;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	CompanyMapper companyMapper;
	
	
	public Collection<Company> getAllCompanies() {
		
		return companyRepository.findAllWithEmployees();
	}
	
	
	public Collection<Company> getAllCompaniesWithoutEmployees() {
		
		return companyRepository.findAll();
	}
	
	
	public Company getCompanyById(long id) {
		
		return companyRepository.findOneWithEmployees(id);
	}
	
	@Transactional
	public Company overwriteCompany(long id, Company comp) {
		
		return this.saveCompany(id, comp);
	}
	
	@Transactional
	public Company saveCompany(long id, Company comp) {
		
		comp.setId(id);
		companyRepository.save(comp);
		return comp;
	}
	
	@Transactional
	public void deleteCompany(long id) {
	
		companyRepository.deleteById(id);
	}
	
	@Transactional
	public Company editEmployee(long id, Employee employee) {
		
		Company company = companyRepository.findById(id).get();
		company.addEmployee(employee);
		
		employeeRepository.save(employee);
		return company;
	}
	
	@Transactional
	public Company removeEmployee(long id, long employeeId) {
		
		Company company = companyRepository.findById(id).get();
		Employee employee = employeeRepository.findById(employeeId).get();
		employee.setCompany(null);
		company.getEmployeeList().remove(employee);
		
		employeeRepository.save(employee);
		companyRepository.save(company);
		
		return company;
	}
	
	@Transactional
	public Company editEmployeeList(List<Employee> employeeList, long id) {
		
		Company company = companyRepository.findById(id).get();
		
		company.getEmployeeList().forEach(a -> a.setCompany(null));
		company.getEmployeeList().clear();
		
		for (Employee employee : employeeList) 
		{	
			company.addEmployee(employee);
			employeeRepository.save(employee);
		}
		
		return company;
	}
	
	
	public Collection<Company> employeeHasHigherSalaryThan(int salary) {
		
		return companyRepository.findBySalaryGreaterThanEqual(salary);
	}
	
	
	public Collection<Company> employeeCountGreaterThan(int limit) {
		
		return companyRepository.findByEmployeeCountGreaterThan(limit);
	}
	
	
	public Collection<Company> averageSalaryPerPosition(int companyId) {
		
		return companyRepository.averageSalaryByPosition(companyId);
	}
	
}
