package fr.dta.linksport.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.dta.linksport.domain.Publication;
import fr.dta.linksport.domain.User;

public interface IPubDao extends JpaRepository<Publication, Long> {
	//@Query("SELECT * FROM publication ORDER BY createdAt DESC")
	List<Publication> findAllByOrderByCreatedAtDesc();
	List<Publication> findByUser(User user);

}
