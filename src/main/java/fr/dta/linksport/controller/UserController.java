package fr.dta.linksport.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.dta.linksport.dao.ISportDao;
import fr.dta.linksport.dao.IUserDao;
import fr.dta.linksport.domain.Sport;
import fr.dta.linksport.domain.User;
import fr.dta.linksport.exception.ResourceNotFoundException;
import fr.dta.linksport.model.ContactInformation;
import fr.dta.linksport.payload.UserIdentityAvailability;
import fr.dta.linksport.payload.UserSummary;
import fr.dta.linksport.security.CurrentUser;
import fr.dta.linksport.security.UserPrincipal;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private IUserDao userDao;

	@Autowired
	private ISportDao sportDao;

	//	@Autowired
	//	private SearchService search;

	// @GetMapping("/users")
	// public List<User> getAllUsers() {
	// return this.userDao.getAll();
	// }
	//
	@GetMapping("/users/{id}")
	public Optional<User> getUserById(@PathVariable Long id) {
		return this.userDao.findById(id);
	}

	@GetMapping("/users/search")
	public Optional<User> search(HttpServletRequest req) {
		String name = req.getParameter("name");
		System.out.println(name);
		return this.userDao.findByUsernameOrEmail(name, name);
	}
	//
	//	@GetMapping("/users/searchterm")
	//	public Page<User> getPeople(
	//			@RequestParam(name = "searchTerm", defaultValue = "", required = false) String searchTerm,
	//			@PageableDefault(size = 20) Pageable pageRequest) {
	//		final Page<User> findUser = search.getPeople(searchTerm, pageRequest);
	//
	//				return findUser.map(User::new);
	////		return findUser;
	//	}

	@GetMapping("/users/suggestion/{id}")
	public List<User> getSuggestion(@PathVariable Long id){
		Sport sport = sportDao.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("sport", "id", id));
		return this.userDao.findBySport(sport);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<String> updateUser(@PathVariable Long id, @Valid @RequestBody ContactInformation contact) {
		User user = userDao.findById(id).orElseThrow(() -> new ResourceNotFoundException("user", "id", id));
		if(!user.getId().equals(contact.getId())) {
			return ResponseEntity.badRequest().contentType(MediaType.TEXT_PLAIN).body("Erreur de mis à jour du profile");
		}

		//		final String oldEmail = user.getEmail();
		//		final String newEmail = contact.getEmail();
		//
		//		if(!oldEmail.equals(newEmail)) {
		//			final User result = userDao.findByUsernameOrEmail(newEmail, newEmail).orElseThrow(() -> new ResourceNotFoundException("user", "email", newEmail));
		//			if(result != null) {
		//				return ResponseEntity.badRequest().contentType(MediaType.TEXT_PLAIN).body("Ce mail est déja utiliser dans un autre profil");
		//			}
		//		}

		if(contact.getEmail()!="") {
			user.setEmail(contact.getEmail());
		}
		if(contact.getPhone()!="") {
			user.setPhone(contact.getPhone());
		}
		if(contact.getDescription()!="") {
			user.setDescription(contact.getDescription());
		}
		if(contact.getZipCode()!=0) {
			user.setZipCode(contact.getZipCode());
		}
		userDao.save(user);
		return ResponseEntity.ok().build();
	}


	//
	// @GetMapping("/user/{name}")
	// public User getUserByName(@PathVariable String name) {
	// return this.userService.getByName(name);
	// }
	//
	// @PostMapping("/users")
	// public User addUser(@Valid @RequestBody User user) {
	// return this.userService.saveUser(user);
	// }
	//
	// @PutMapping("/users/{id}")
	// public User updateUser(@PathVariable Long id, @Valid @RequestBody User user)
	// {
	// return this.userService.ChangeUserData(id, user);
	// }
	//
	// @DeleteMapping("/users/{id}")
	// public ResponseEntity<?> deleteUser(@PathVariable Long id) {
	// return this.userService.deleteUser(id);
	// }

	@GetMapping("/user/me")
	public UserSummary getCurrentUser(@CurrentUser UserPrincipal currentUser) {
		UserSummary userSummary = new UserSummary(currentUser.getId(), currentUser.getUsername());
		return userSummary;
	}

	@GetMapping("/user/checkUsername")
	public UserIdentityAvailability checkUsernameAvailability(@RequestParam(value = "username") String username) {
		Boolean isAvailable = !this.userDao.existsByUsername(username);
		return new UserIdentityAvailability(isAvailable);
	}

	@GetMapping("/user/checkEmailAviability")
	public UserIdentityAvailability checkEmailAvailability(@RequestParam(value = "email") String email) {
		Boolean isAvailable = !this.userDao.existsByEmail(email);
		return new UserIdentityAvailability(isAvailable);
	}

}
