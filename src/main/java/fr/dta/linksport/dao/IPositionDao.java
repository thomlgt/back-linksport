package fr.dta.linksport.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.dta.linksport.domain.Position;

public interface IPositionDao extends JpaRepository<Position, Long>{
	Optional<Position> findByPositionname(String Positionname);
	List<Position> findByIdIn(List<Long> PositionIds);

}
