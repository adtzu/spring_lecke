package hu.webuni.hr.atold.mapper;

import java.util.Collection;
import java.util.List;
import org.mapstruct.Mapper;

import hu.webuni.hr.atold.dto.EmployeeDto;
import hu.webuni.hr.atold.model.Employee;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
	
	EmployeeDto employeeToDto(Employee employee);
	
	Employee dtoToEmployee(EmployeeDto employee);
	
	List<Employee> employeeDtoListToEmployeeList(List<EmployeeDto> employeeDto);
	
	Collection<EmployeeDto> employeeDtoListToEmployeeList(Collection<Employee> employeeDto);

}
