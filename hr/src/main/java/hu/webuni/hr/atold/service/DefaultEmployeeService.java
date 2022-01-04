package hu.webuni.hr.atold.service;

import org.springframework.beans.factory.annotation.Value;
import hu.webuni.hr.atold.model.Employee;


public class DefaultEmployeeService extends EmployeeService {
	
	@Value("${hr.payraise.default.percent}")
	private int defaultPercent = 0;

	@Override
	public int getPayRaisePercent(Employee employee) {
		
		return defaultPercent;
	}

}
