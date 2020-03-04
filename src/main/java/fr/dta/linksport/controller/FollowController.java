package fr.dta.linksport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.dta.linksport.dao.IUserDao;
import fr.dta.linksport.domain.User;
import fr.dta.linksport.exception.ResourceNotFoundException;
import fr.dta.linksport.service.FollowService;

@RestController
@RequestMapping("/api/follow")
public class FollowController {
	@Autowired
	IUserDao userDao;
		/* Ajout d'un follow */
	    @PutMapping("/add/{followerId}/{followedId}")
	    public ResponseEntity<Void> addFollow(
	            @PathVariable("followerId") Long idr,
	            @PathVariable("followedId") Long idd) {

	        User follower = userDao.findById(idr).orElseThrow(() -> new ResourceNotFoundException("user", "id", idr));
	        User followed = userDao.findById(idd).orElseThrow(() -> new ResourceNotFoundException("user", "id", idd));
	        
	        FollowService.addFollow(follower, followed);

	        return ResponseEntity.ok().build();
	    }

	    /* Suppression d'un follow */
	    @PutMapping("/remove/{followerId}/{followedId}")
	    public ResponseEntity<Void> removeFriend(
	            @PathVariable("followerId") Long idr,
	            @PathVariable("followedId") Long idd) {

	    	User follower = userDao.findById(idr).orElseThrow(() -> new ResourceNotFoundException("user", "id", idr));
	        User followed = userDao.findById(idd).orElseThrow(() -> new ResourceNotFoundException("user", "id", idd));

	        FollowService.removeFollow(follower, followed);

	        return ResponseEntity.ok().build();
	    }
}
