package hu.webuni.hr.atold.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.webuni.hr.atold.model.Position;

public interface PositionRepository extends JpaRepository<Position, Long> {

}
