package fr.dta.linksport.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "sport")
@NoArgsConstructor
@AllArgsConstructor
public class Sport implements Serializable{
	//	public static final Sport Football = new Sport(1L, "Football");
	//	public static final Sport Basketball = new Sport(2L, "Basketball");
	//	public static final Sport Handball = new Sport(3L, "Handball");
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 45)
	private String sportname;

	public Sport(long id, String sportname) {
		this.id = id;
		this.sportname = sportname;
	}

	public Sport(long id) {
		this.id = id;
	}

}

