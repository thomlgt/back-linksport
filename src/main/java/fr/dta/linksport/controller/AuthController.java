package fr.dta.linksport.controller;

import java.net.URI;
import java.util.Collections;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import fr.dta.linksport.dao.IGenderDao;
import fr.dta.linksport.dao.ILevelDao;
import fr.dta.linksport.dao.IPositionDao;
import fr.dta.linksport.dao.IRoleDao;
import fr.dta.linksport.dao.ISportDao;
import fr.dta.linksport.dao.IUserDao;
import fr.dta.linksport.domain.Gender;
import fr.dta.linksport.domain.Level;
import fr.dta.linksport.domain.Position;
import fr.dta.linksport.domain.Role;
import fr.dta.linksport.domain.RoleName;
import fr.dta.linksport.domain.Sport;
import fr.dta.linksport.domain.User;
import fr.dta.linksport.exception.AppException;
import fr.dta.linksport.exception.ResourceNotFoundException;
import fr.dta.linksport.payload.ApiResponse;
import fr.dta.linksport.payload.JwtAuthenticationResponse;
import fr.dta.linksport.payload.LoginRequest;
import fr.dta.linksport.payload.SignUpCoachRequest;
import fr.dta.linksport.payload.SignUpPlayerRequest;
import fr.dta.linksport.payload.SignUpTeamRequest;
import fr.dta.linksport.security.JwtTokenProvider;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	IUserDao userRepository;
	@Autowired
	IRoleDao roleRepository;
	@Autowired
	ISportDao sportRepository;
	@Autowired
	ILevelDao levelRepository;
	@Autowired
	IGenderDao genderRepository;
	@Autowired
	IPositionDao positionRepository;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	JwtTokenProvider tokenProvider;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		Authentication authentication = this.authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsernameOrEmail(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = this.tokenProvider.generateToken(authentication);
		return ResponseEntity.ok(new JwtAuthenticationResponse(jwt, this.userRepository
				.findByUsernameOrEmail(loginRequest.getUsernameOrEmail(), loginRequest.getUsernameOrEmail())));
	}

	// Coach SignUp
	@PostMapping("/signup/coach")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpCoachRequest signUpCoachRequest) {

		if (this.userRepository.existsByUsername(signUpCoachRequest.getUsername())) {
			return new ResponseEntity<>(new ApiResponse(false, "Ce nom d'utilisateur existe déjà"),
					HttpStatus.BAD_REQUEST);
		}
		if (this.userRepository.existsByEmail(signUpCoachRequest.getEmail())) {
			return new ResponseEntity<>(new ApiResponse(false, "Adresse e-mail déjà utilisée!"),
					HttpStatus.BAD_REQUEST);
		}
		Level level = levelRepository.findById(signUpCoachRequest.getIdLevel())
				.orElseThrow(() -> new ResourceNotFoundException("level", "id", signUpCoachRequest.getIdLevel()));
		Gender gender = genderRepository.findById(signUpCoachRequest.getIdGender())
				.orElseThrow(() -> new ResourceNotFoundException("gender", "id", signUpCoachRequest.getIdGender()));
		System.out.println("test" + level);
		Sport sport = sportRepository.findById(signUpCoachRequest.getIdSport())
				.orElseThrow(() -> new ResourceNotFoundException("sport", "id", signUpCoachRequest.getIdSport()));

		// Creating coach profile
		User coach = new User();
		coach.createCoach(signUpCoachRequest.getUsername(), signUpCoachRequest.getEmail(),
				signUpCoachRequest.getPassword(), signUpCoachRequest.getPhone(), signUpCoachRequest.getBirthDate(),
				signUpCoachRequest.getZipCode(), signUpCoachRequest.getDescription(), level, sport,
				signUpCoachRequest.getFirstname(), signUpCoachRequest.getLastname(), gender);

		coach.setPassword(this.passwordEncoder.encode(coach.getPassword()));


		Role userRole = this.roleRepository.findByName(RoleName.ROLE_COACH)
				.orElseThrow(() -> new AppException("User Role not set."));
		coach.setRole(Collections.singleton(userRole));


		User result = this.userRepository.save(coach);
		URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users/{username}")
				.buildAndExpand(result.getUsername()).toUri();
		return ResponseEntity.created(location).body(new ApiResponse(true, "Utilisateur enregistré avec succés !"));
	}

	// Team SignUp
	@PostMapping("/signup/team")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpTeamRequest signUpTeamRequest) {
		if (this.userRepository.existsByUsername(signUpTeamRequest.getUsername())) {
			return new ResponseEntity<>(new ApiResponse(false, "Ce nom d'utilisateur existe déjà"),
					HttpStatus.BAD_REQUEST);
		}
		if (this.userRepository.existsByEmail(signUpTeamRequest.getEmail())) {
			return new ResponseEntity<>(new ApiResponse(false, "Adresse e-mail déjà utilisée!"),
					HttpStatus.BAD_REQUEST);
		}
		Level level = levelRepository.findById(signUpTeamRequest.getIdLevel())
				.orElseThrow(() -> new ResourceNotFoundException("level", "id", signUpTeamRequest.getIdLevel()));
		System.out.println("test" + level);
		Sport sport = sportRepository.findById(signUpTeamRequest.getIdSport())
				.orElseThrow(() -> new ResourceNotFoundException("sport", "id", signUpTeamRequest.getIdSport()));

		// Creating team profile
		User team = new User();
		team.createTeam(signUpTeamRequest.getUsername(), signUpTeamRequest.getEmail(), signUpTeamRequest.getPassword(),
				signUpTeamRequest.getPhone(), signUpTeamRequest.getCreationDate(), signUpTeamRequest.getZipCode(),
				signUpTeamRequest.getDescription(), level, sport, signUpTeamRequest.getName());

		team.setPassword(this.passwordEncoder.encode(team.getPassword()));

		Role userRole = this.roleRepository.findByName(RoleName.ROLE_TEAM)
				.orElseThrow(() -> new AppException("User Role not set."));
		team.setRole(Collections.singleton(userRole));

		User result = this.userRepository.save(team);
		URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users/{username}")
				.buildAndExpand(result.getUsername()).toUri();
		return ResponseEntity.created(location).body(new ApiResponse(true, "Utilisateur enregistré avec succés !"));
	}

	// Player SignUp
	@PostMapping("/signup/player")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpPlayerRequest signUpPlayerRequest) {
		if (this.userRepository.existsByUsername(signUpPlayerRequest.getUsername())) {
			return new ResponseEntity<>(new ApiResponse(false, "Ce nom d'utilisateur existe déjà"),
					HttpStatus.BAD_REQUEST);
		}
		if (this.userRepository.existsByEmail(signUpPlayerRequest.getEmail())) {
			return new ResponseEntity<>(new ApiResponse(false, "Adresse e-mail déjà utilisée!"),
					HttpStatus.BAD_REQUEST);
		}
		Level level = levelRepository.findById(signUpPlayerRequest.getIdLevel())
				.orElseThrow(() -> new ResourceNotFoundException("level", "id", signUpPlayerRequest.getIdLevel()));
		Sport sport = sportRepository.findById(signUpPlayerRequest.getIdSport())
				.orElseThrow(() -> new ResourceNotFoundException("sport", "id", signUpPlayerRequest.getIdSport()));
		Position position = positionRepository.findById(signUpPlayerRequest.getIdPosition()).orElseThrow(
				() -> new ResourceNotFoundException("position", "id", signUpPlayerRequest.getIdPosition()));
		Gender gender = genderRepository.findById(signUpPlayerRequest.getIdGender())
				.orElseThrow(() -> new ResourceNotFoundException("gender", "id", signUpPlayerRequest.getIdGender()));

		// Creating player profile
		User player = new User();
		player.createPlayer(signUpPlayerRequest.getUsername(), signUpPlayerRequest.getEmail(),
				signUpPlayerRequest.getPassword(), signUpPlayerRequest.getPhone(), signUpPlayerRequest.getBirthDate(),
				signUpPlayerRequest.getZipCode(), signUpPlayerRequest.getDescription(), level, sport,
				signUpPlayerRequest.getFirstname(), signUpPlayerRequest.getLastname(), gender, position);

		player.setPassword(this.passwordEncoder.encode(player.getPassword()));

		Role userRole = this.roleRepository.findByName(RoleName.ROLE_PLAYER)
				.orElseThrow(() -> new AppException("User Role not set."));
		player.setRole(Collections.singleton(userRole));

		User result = this.userRepository.save(player);
		URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users/{username}")
				.buildAndExpand(result.getUsername()).toUri();
		return ResponseEntity.created(location).body(new ApiResponse(true, "Utilisateur enregistré avec succés !"));
	}

}
