package fr.dta.linksport.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.dta.linksport.dao.IUserDao;
import fr.dta.linksport.domain.User;
import fr.dta.linksport.security.UserPrincipal;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	IUserDao userDao;
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String usernameOrEmail)
			throws UsernameNotFoundException {
		// Let people login with either username or email
		User user = this.userDao.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
				.orElseThrow(() ->
				new UsernameNotFoundException("User not found with username or email : " + usernameOrEmail)
						);
		return UserPrincipal.create(user);
	}
	// This method is used by JWTAuthenticationFilter
	@Transactional
	public UserDetails loadUserById(Long id) {
		User user = this.userDao.findById(id).orElseThrow(
				() -> new UsernameNotFoundException("User not found with id : " + id)
				);
		return UserPrincipal.create(user);
		
		//	@Autowired
		//	private IUserDao userDao;
		//
		//	public List<User> getAll() {
		//		return this.userDao.findAll();
		//	}
		//
		//	public User saveUser(User user) {
		//		return this.userDao.save(user);
		//	}
		//
		//	public User getById(Long id) {
		//		return this.userDao.findById(id).orElseThrow(()-> new ResourceNotFoundException("User", "id", id));
		//	}
		//
		//	public User getByName(String name) {
		//		return this.userDao.findByLastname(name).orElseThrow(()-> new ResourceNotFoundException("User", "lastname", name));
		//	}
		//
		//	public User ChangeUserData(Long id, User modifiedUser) {
		//		return this.userDao.findById(id).map((user)->{
		//			user.setFirstname(modifiedUser.getFirstname());
		//			user.setLastname(modifiedUser.getLastname());
		//			user.setAddress(modifiedUser.getAddress());
		//			user.setBirthDate(modifiedUser.getBirthDate());
		//			return this.userDao.save(user);
		//		}).orElseThrow(()-> new ResourceNotFoundException("User", "id", id));
		//	}
		//
		//	public ResponseEntity<?> deleteUser(Long id) {
		//		return this.userDao.findById(id).map((user)->{
		//			this.userDao.delete(user);
		//			return ResponseEntity.ok().build();
		//		}).orElseThrow(()-> new ResourceNotFoundException("User", "id", id));
		//	}

	}

	public List<User> findAllUsers(){
		return this.userDao.findAll();
	}

	public User saveUser(User user) {
		return this.userDao.save(user);
	}

}