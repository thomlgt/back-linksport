package fr.dta.linksport.model;

import java.io.Serializable;

import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessagePost implements Serializable{
	
	private long sender;
	private long recipient;
	@Size (min = 1, max = 10000)
	private String body;

}
