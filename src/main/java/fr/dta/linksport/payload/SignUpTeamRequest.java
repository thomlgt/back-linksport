package fr.dta.linksport.payload;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class SignUpTeamRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotEmpty
	@Size(min = 3)
	private String username;

	private String name;

	@NotEmpty
	@Size(max = 60)
	@Email
	private String email;

	@NotEmpty
	@Size(min = 3)
	private String password;

	private String phone;

	private int zipCode;


	@DateTimeFormat (pattern="dd/MM/yyyy")
	private Date creationDate;

	private String description;

	// Les elements d'ici en bas ce sont les foering keys (FK) et ce quil y a dans les tables des FK

	private long idLevel;

	private long idSport;

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getZipCode() {
		return this.zipCode;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	public Date getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getIdLevel() {
		return this.idLevel;
	}

	public void setIdLevel(long idLevel) {
		this.idLevel = idLevel;
	}

	public long getIdSport() {
		return this.idSport;
	}

	public void setIdSport(long idSport) {
		this.idSport = idSport;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


}
