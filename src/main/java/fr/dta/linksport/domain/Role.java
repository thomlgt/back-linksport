package fr.dta.linksport.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;
@Entity
@Table(name = "role")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	@NaturalId
	@Column(length = 60)
	private RoleName name;

	//	@ManyToMany(fetch = FetchType.LAZY)
	//	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	//	private Set<User> user = new HashSet<>();

	public Role() {
	}
	public Role(RoleName name) {
		this.name = name;
	}
	public Long getId() {
		return this.id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public RoleName getName() {
		return this.name;
	}
	public void setName(RoleName name) {
		this.name = name;
	}
	//	public Set<User> getUser() {
	//		return user;
	//	}
	//	public void setUser(Set<User> user) {
	//		this.user = user;
	//	}



}
