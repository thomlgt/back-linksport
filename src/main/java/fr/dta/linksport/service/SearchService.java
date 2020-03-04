package fr.dta.linksport.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import fr.dta.linksport.dao.IUserDao;
import fr.dta.linksport.domain.User;

public class SearchService {
	@Autowired
	IUserDao userRepository;

	@Transactional(readOnly = true)
	public Page<User> getPeople(String searchTerm, Pageable pageRequest) {
		return userRepository.findUsers(searchTerm, pageRequest);
	}
}
