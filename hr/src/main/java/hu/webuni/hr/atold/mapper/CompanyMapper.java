package hu.webuni.hr.atold.mapper;

import java.util.Collection;

import org.mapstruct.Mapper;

import hu.webuni.hr.atold.dto.CompanyDto;
import hu.webuni.hr.atold.model.Company;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
	
	Collection<CompanyDto> companiesToCompaiesDto(Collection<Company> companies);
	
	CompanyDto companyToDto(Company company);
	
	Company dtoToCompany(CompanyDto company);

}
