package fr.dta.linksport.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.dta.linksport.domain.Role;
import fr.dta.linksport.domain.RoleName;

public interface IRoleDao extends JpaRepository<Role, Long>{
	Optional<Role> findByName(RoleName role);
}
