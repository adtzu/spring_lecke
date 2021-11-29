package hu.webuni.hr.atold.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import hu.webuni.hr.atold.model.Employee;

@Service
public class DefaultEmployeeService implements EmployeeService {
	
	@Value("hr.payraise.default.percent")
	private int defaultPercent;

	@Override
	public int getPayRaisePercent(Employee employee) {
		
		return defaultPercent;
	}

}
