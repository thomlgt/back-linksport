package fr.dta.linksport.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.dta.linksport.domain.Sport;


public interface ISportDao extends JpaRepository<Sport, Long>{
	Optional<Sport> findBySportname(String sportname);
	List<Sport> findByIdIn(List<Long> sportIds);

}
