package hu.webuni.hr.atold.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import hu.webuni.hr.atold.dto.DayOffDto;
import hu.webuni.hr.atold.model.DayOff;

@Mapper(componentModel = "spring")
public interface DayOffMapper {
	
	DayOff dayOffDtoTomodel(DayOffDto dto);
	DayOffDto modelToDto(DayOff dayOff);
	List<DayOffDto> listModelToDto(List<DayOff> list);	

}
