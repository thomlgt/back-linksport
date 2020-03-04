package fr.dta.linksport.model;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;
import java.io.Serializable;
import java.util.Date;

import fr.dta.linksport.domain.Message;
import fr.dta.linksport.domain.User;

@Data
@ToString
public class MessageView implements Serializable {
    private Long senderId;
    private String senderName;
    private Long recipientId;
    private String recipientName;
    private String body;
    private Long interlocutor;
    private String avatar;
    private Date posted;
    public MessageView(Message message) {
        final User profile; //here i should add current profile get method
        final User sender = message.getSender();
        final User recipient = message.getRecipient();
        this.senderId = sender.getId();
        this.senderName = sender.getUsername();
        this.recipientId = recipient.getId();
        this.recipientName = recipient.getUsername();
        this.body = message.getBody().replace("\n", "\\n");
        this.posted = message.getPosted();
        /*if (profile.getId().equals(sender.getId())) {
            this.interlocutor = recipient.getId();
            this.avatar = AvatarService.getAvatar(recipient.getId(), recipient.getFullName());
        } else {
            this.interlocutor = sender.getId();
            this.avatar = AvatarService.getAvatar(sender.getId(), sender.getFullName());
        }*/
    }
}