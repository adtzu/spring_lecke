package hu.webuni.hr.atold.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import hu.webuni.hr.atold.model.Employee;

@Service
public class SmartEmployeeService implements EmployeeService {
	
	@Value("${hr.payraise.special.first.limit}")
	private int firstLimit;
	
	@Value("${hr.payraise.special.first.percent}")
	private int firstPercent;
	
	@Value("${hr.payraise.special.second.limit}")
	private int secondLimit;
	
	@Value("${hr.payraise.special.second.percent}")
	private int secondPercent;
	
	@Value("${hr.payraise.special.third.limit}")
	private int thirdLimit;
	
	@Value("${hr.payraise.special.third.percent}")
	private int thirdPercent;
	
	@Value("${hr.payraise.special.default.percent}")
	private int defaultPercent;
	
	@Override
	public int getPayRaisePercent(Employee employee) {
		
		
		
		if ( employee.getEnterance().until(LocalDateTime.now(), ChronoUnit.MONTHS) >= thirdLimit ) {
			
			return thirdPercent;
		}
		else if ( employee.getEnterance().until(LocalDateTime.now(), ChronoUnit.MONTHS) >= secondLimit ) {
		
			return secondPercent;
		}
		else if ( employee.getEnterance().until(LocalDateTime.now(), ChronoUnit.MONTHS) >= firstLimit ) {
			
			return firstPercent;
		}
		else {
			return defaultPercent;
		}			
	}
}
