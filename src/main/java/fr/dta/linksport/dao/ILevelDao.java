package fr.dta.linksport.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.dta.linksport.domain.Level;

public interface ILevelDao extends JpaRepository<Level, Long>{
	Optional<Level> findByLevelname(String levelName);
	List<Level> findByIdIn(List<Long> levelIds);

}
