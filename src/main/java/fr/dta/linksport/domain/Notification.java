package fr.dta.linksport.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Notification {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "messages_id")
	private long messages;
	
	@Column(name = "publication_id")
	private Publication publication;
	
	@Column(name = "follower_id")
	private User follower;
	
	public Notification() {
		
	}
	
}
