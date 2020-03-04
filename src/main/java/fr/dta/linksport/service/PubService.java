package fr.dta.linksport.service;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.dta.linksport.dao.IPubDao;
import fr.dta.linksport.domain.Publication;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PubService {
	private final IPubDao pubRepositoroy;
	public List<Publication> getAllPublicattions(){
		return pubRepositoroy.findAllByOrderByCreatedAtDesc();
	}
	public Publication send(Publication publication) {
		return pubRepositoroy.save(publication);
	}
}
