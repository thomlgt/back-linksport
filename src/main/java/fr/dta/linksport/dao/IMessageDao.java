package fr.dta.linksport.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.dta.linksport.domain.Message;
import fr.dta.linksport.domain.User;

public interface IMessageDao extends JpaRepository <Message, Long>{
	
		@Query("SELECT m " +
	            "FROM Message m " +
	            "WHERE m.sender = :sender AND m.recipient = :recipient " +
	            "   OR m.sender = :recipient AND m.recipient = :sender " +
	            "ORDER BY m.posted DESC")
	    List<Message> findByRecipientOrSenderOrderByPostedDesc(
	            @Param("sender") User sender,
	            @Param("recipient") User recipient);
	    @Query("SELECT m " +
	            "FROM Message m " +
	            "WHERE m.id IN (" +
	            "   SELECT MAX(l.id) " +
	            "   FROM Message l " +
	            "   WHERE l.sender = :user OR l.recipient = :user " +
	            "   GROUP BY " +
	            "       CASE " +
	            "           WHEN l.recipient = :user THEN l.sender " +
	            "           WHEN l.sender = :user THEN l.recipient " +
	            "           ELSE :user " +
	            "       END) " +
	            "ORDER BY m.posted DESC")
	    List<Message> findLastMessagesByUser(@Param("user") User user);

}
