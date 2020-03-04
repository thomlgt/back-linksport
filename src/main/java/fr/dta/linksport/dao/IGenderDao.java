package fr.dta.linksport.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.dta.linksport.domain.Gender;

public interface IGenderDao extends JpaRepository<Gender, Long>{
	Optional<Gender> findByDescription(String description);
}
