package fr.dta.linksport.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NaturalId;
import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.annotation.JsonIgnore;

import fr.dta.linksport.model.AuditModel;
import lombok.Getter;
import lombok.Setter;
@Entity
@Table(name = "user", uniqueConstraints =
{ @UniqueConstraint(columnNames = { "username" }),
		@UniqueConstraint(columnNames = { "email" }) })
public class User extends AuditModel implements Serializable {


	private static final long serialVersionUID = 1L;

	public User() {
		super();
	}


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="username", nullable = false)
	@NotBlank
	@Size(max = 45)
	private String username;

	@NaturalId
	@NotBlank
	@Size(max = 60)
	@Email
	private String email;

	@Column(length = 60, nullable = false)
	private String password;

	@Column(length = 15)
	private String phone;

	@Temporal(TemporalType.DATE)
	private Date birthDate;

	@Column(length = 5, nullable = false)
	private int zipCode;

	@Size(min = 10, max = 1000)
	private String description;

	@Value("person.png")
	@Column(length = 25, nullable = true)
	@Getter @Setter
	private String avatar;

	@JoinColumn(name = "level_id")
	@ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Embedded
	private Level level;

	@JoinColumn(name = "sport_id")
	@ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Embedded
	private Sport sport;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> role = new HashSet<>();

	/* Specialization */

	@Size(max = 45)
	private String firstname;

	@NotBlank
	@Size(max = 100)
	private String name;

	@JoinColumn(name = "gender_id")
	@ManyToOne(optional = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Gender gender;

	@JoinColumn(name = "position_id", nullable = true)
	@ManyToOne(optional = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Position position;

	/* --------------------- */

	/* GETTERS N SETTERS */
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public int getZipCode() {
		return zipCode;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	@Value("true")
	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	//	public Set<User> getFollowers() {
	//		return followers;
	//	}
	//
	//	public void setFollowers(Set<User> followers) {
	//		this.followers = followers;
	//	}
	//
	//	public Set<User> getFolloweds() {
	//		return followeds;
	//	}
	//
	//	public void setFolloweds(Set<User> followeds) {
	//		this.followeds = followeds;
	//	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Value("true")
	private boolean enable;

	//Follow entity
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "followers",
	joinColumns = @JoinColumn(name = "followed_id"),
	inverseJoinColumns = @JoinColumn(name = "follower_id"))
	@Getter @Setter @JsonIgnore
	private Set<User> followers = new HashSet<>();

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "followers",
	joinColumns = @JoinColumn(name = "follower_id"),
	inverseJoinColumns = @JoinColumn(name = "followed_id"))
	@Getter @Setter @JsonIgnore
	private Set<User> followeds = new HashSet<>();

	public boolean hasFollower(User follower) {
		return followers.contains(follower);
	}

	public void addFollower(User follower) {
		followers.add(follower);
		follower.followeds.add(this);
	}

	public void removeFollow(User follower) {
		followers.remove(follower);
		follower.followeds.remove(this);
	}

	public boolean isFollowed(User user) {
		return followeds.contains(user);
	}

	//Creations users methods
	//Creation of coach
	public User createCoach(@Size(max = 45) String username, @Size(max = 60) @Email String email,
			String password, String phone, Date birthDate, int zipCode, @Size(min = 10, max = 1000) String description,
			Level level, Sport sport, @Size(max = 45) String firstname,
			@Size(max = 100) String name, Gender gender) {
		User coach = null;
		this.username = username;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.birthDate = birthDate;
		this.zipCode = zipCode;
		this.description = description;
		this.level = level;
		this.sport = sport;
		this.firstname = firstname;
		this.name = name;
		this.gender = gender;
		return coach;
	}

	//Creation of team
	public User createTeam(@Size(max = 45) String username, @Size(max = 60) @Email String email,
			String password, String phone, Date birthDate, int zipCode, @Size(min = 10, max = 1000) String description,
			Level level, Sport sport, @Size(max = 100) String name) {
		User team = null;
		this.username = username;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.birthDate = birthDate;
		this.zipCode = zipCode;
		this.description = description;
		this.level = level;
		this.sport = sport;
		this.name = name;
		return team;
	}

	//Creation of player
	public User createPlayer(@Size(max = 45) String username, @Size(max = 60) @Email String email,
			String password, String phone, Date birthDate, int zipCode, @Size(min = 10, max = 1000) String description,
			Level level, Sport sport, @Size(max = 45) String firstname,
			@Size(max = 100) String name, Gender gender, Position position) {
		User player = null;
		this.username = username;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.birthDate = birthDate;
		this.zipCode = zipCode;
		this.description = description;
		this.level = level;
		this.sport = sport;
		this.firstname = firstname;
		this.name = name;
		this.gender = gender;
		this.position = position;
		return player;
	}



	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public Sport getSport() {
		return sport;
	}

	public void setSport(Sport sport) {
		this.sport = sport;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public Set<Role> getRole() {
		return this.role;
	}

	public void setRole(Set<Role> role) {
		this.role = role;
	}

}
