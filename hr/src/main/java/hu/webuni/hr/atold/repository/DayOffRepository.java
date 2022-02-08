package hu.webuni.hr.atold.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import hu.webuni.hr.atold.model.DayOff;

public interface DayOffRepository extends JpaRepository<DayOff, Long>, JpaSpecificationExecutor<DayOff>  {
	
	@Query("SELECT d FROM DayOff d")
	Page<DayOff> findAllPage(Pageable page);

}
