package hu.webuni.hr.atold.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.webuni.hr.atold.dto.DayOffDto;
import hu.webuni.hr.atold.mapper.DayOffMapper;
import hu.webuni.hr.atold.service.DayOffService;

@RestController
@RequestMapping("/api/dayoff")
public class DayOffController {
	
	private static final int linePerPage = 10;
	
	@Autowired
	DayOffService dayOffService;
	
	@Autowired
	DayOffMapper dayOffMapper;

	
	
	@PostMapping("/list/filter") 
	public List<DayOffDto> findHoliday(@RequestBody DayOffDto dayOff){
		 
		return dayOffMapper.listModelToDto(dayOffService.findDayOff(dayOffMapper.dayOffDtoTomodel(dayOff)));
	}	
	
	@GetMapping("/list/{page}/{orderer}")
	public List<DayOffDto> getDayoffs(@PathVariable int page, @PathVariable String orderer) {
		
		Pageable pageable = PageRequest.of(page, linePerPage, Sort.by(orderer));
		return dayOffMapper.listModelToDto(dayOffService.listAllPage(pageable).getContent());
	}
	
	@GetMapping("/approve/{dayOffId}/{approverId}")
	public DayOffDto approve(@PathVariable long dayOffId, @PathVariable long approverId) {
		
		return dayOffMapper.modelToDto(dayOffService.approveDayOff(dayOffId, approverId));
	}
	
	@PostMapping("/{employeeId}")
	public DayOffDto addNew(@RequestBody DayOffDto dayOff, @PathVariable long employeeId) {
		
		return dayOffMapper.modelToDto((dayOffService.addNew(employeeId, dayOffMapper.dayOffDtoTomodel(dayOff))));
	}
	
	@DeleteMapping("/{dayOffId}")
	public void deleteDayOff(@PathVariable long dayOffId) {
		
		dayOffService.delete(dayOffId);
	}

}
