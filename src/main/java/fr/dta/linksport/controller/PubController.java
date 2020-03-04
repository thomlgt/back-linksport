package fr.dta.linksport.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import fr.dta.linksport.dao.IPubDao;
import fr.dta.linksport.dao.IUserDao;
import fr.dta.linksport.domain.Publication;
import fr.dta.linksport.domain.User;
import fr.dta.linksport.exception.ResourceNotFoundException;
import fr.dta.linksport.model.PublicationPost;
import fr.dta.linksport.payload.ApiResponse;
import fr.dta.linksport.service.PubService;

@RestController
@RequestMapping("/api")
public class PubController {
	@Autowired
	IPubDao pubRepository;
	@Autowired
	IUserDao userRepository;
	@Autowired
	PubService pubService;

	@PostMapping("/pub/send")
	public ResponseEntity<?> send(@Valid @RequestBody PublicationPost publicationPost){
		Publication pub = new Publication();
		pub.setUser(userRepository.findByUsernameOrEmail(publicationPost.getSender(),publicationPost.getSender()).orElseThrow(() -> new ResourceNotFoundException("user", "id", publicationPost.getSender())));
		pub.setText(publicationPost.getBody());
		pub.setCreatedAt(publicationPost.getCreatedAt());
		pubService.send(pub);


		URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users/{username}")
				.buildAndExpand(pub.getUser()).toUri();
		return ResponseEntity.created(location).body(new ApiResponse(true, "Publication enregistré avec succés !"));
	}

	@GetMapping("/pub")
	public List<Publication> getAllPub(){
		return this.pubRepository.findAllByOrderByCreatedAtDesc();
	}

	@GetMapping("/pub/current/{id}")
	public List<Publication> getCurrentPub(@PathVariable Long id) {
		User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("user", "id", id));
		return pubRepository.findByUser(user);

	}

}
