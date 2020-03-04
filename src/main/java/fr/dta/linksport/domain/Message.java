package fr.dta.linksport.domain;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Table(name = "messages")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"sender", "recipient", "posted"})
@ToString(of = {"id", "body"})
public class Message implements Serializable{
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public User getSender() {
	return sender;
}
public void setSender(User sender) {
	this.sender = sender;
}
public User getRecipient() {
	return recipient;
}
public void setRecipient(User recipient) {
	this.recipient = recipient;
}
public String getBody() {
	return body;
}
public void setBody(String body) {
	this.body = body;
}
public Date getPosted() {
	return posted;
}
public void setPosted(Date posted) {
	this.posted = posted;
}
public static long getSerialversionuid() {
	return serialVersionUID;
}
@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Getter
   private Long id;
   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "sender_id")
   @Getter @Setter
   private User sender;
   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "recipient_id")
   @Getter @Setter
   private User recipient;
   @Getter @Setter
   @Column(nullable = false)
   private String body;
   @Column(updatable = false, nullable = false)
   @Temporal(TemporalType.TIMESTAMP)
   @Getter
   private Date posted = new Date();
}