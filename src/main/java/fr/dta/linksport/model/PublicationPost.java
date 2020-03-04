package fr.dta.linksport.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.CreatedDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublicationPost implements Serializable{
	private String sender;
	@Size (min = 1, max = 10000)
	private String body;
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date createdAt;
}
