package fr.dta.linksport.payload;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;


public class SignUpCoachRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotEmpty
	@Size(min = 3)
	private String username;

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public int getZipCode() {
		return zipCode;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	public long getIdGender() {
		return idGender;
	}

	public void setIdGender(long idGender) {
		this.idGender = idGender;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getIdLevel() {
		return idLevel;
	}

	public void setIdLevel(long idLevel) {
		this.idLevel = idLevel;
	}

	public long getIdSport() {
		return idSport;
	}

	public void setIdSport(long idSport) {
		this.idSport = idSport;
	}

	private String firstname;

	private String lastname;

	@NotEmpty
	@Size(max = 60)
	@Email
	private String email;

	@NotEmpty
	@Size(min = 3)
	private String password;

	private String phone;

	private int zipCode;

	private long idGender;


	@DateTimeFormat (pattern="dd/MM/yyyy")
	private Date birthDate;

	private String description;

	// Les elements d'ici en bas ce sont les foering keys (FK) et ce quil y a dans les tables des FK

	private long idLevel;

	private long idSport;

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

}