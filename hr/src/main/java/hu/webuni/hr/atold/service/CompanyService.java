package hu.webuni.hr.atold.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import hu.webuni.hr.atold.model.Company;
import hu.webuni.hr.atold.model.Employee;

@Service
public class CompanyService {
	
	private Map<Long, Company> companies = new HashMap<>();

	public Collection<Company> getAllComanies() {
		
		return companies.values();
	}
	
	
	public Collection<Company> getAllCompaniesWithoutEmployees() {
		
		Map<Long, Company> companies2 = companies.entrySet().stream().collect(Collectors.toMap(k -> (Long)k.getKey(), v -> {v.getValue().setEmployeeList(null); return (Company)v;}));
		return companies2.values();
	}
	
	
	public Company getCompanyById(long id) {
		
		if(companies.containsKey(id))
		{
			return companies.get(id);
		}
		else
		{
			return null;
		}
	}
	
	
	public Company overwriteCompany(long id, Company comp) {
		
		if(companies.containsKey(id))
		{
			return null;
		}

		companies.put(id, comp);
		return comp;
	}
	
	
	public Company saveCompany(long id, Company comp) {
		
		companies.put(id, comp);
		return comp;
	}
	
	
	public void deleteCompany(long id) {
		companies.remove(id);
	}
	
	
	public Company editEmployee(long id, Employee employee) {
		
		List<Employee> currentEmployees = companies.get(id).getEmployeeList();
		currentEmployees.add(employee);
		companies.get(id).setEmployeeList(currentEmployees);
		
		return companies.get(id);
	}
	
	public Company removeEmployee(long id, long employeeId) {
		
		if(!companies.containsKey(id))
		{
			return null;
		}
		
		companies.get(id).getEmployeeList().removeIf(e -> (e.getId() == employeeId));
		return companies.get(id);
	}
	
	public Company editEmployeeList(List<Employee> employeeList, long id) {
		
		if(!companies.containsKey(id))
		{
			return null;
		}
		
		companies.get(id).setEmployeeList(employeeList);
		return companies.get(id);		
	}
	
}
