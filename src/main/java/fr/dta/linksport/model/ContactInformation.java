package fr.dta.linksport.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactInformation implements Serializable{

	@NotNull
	private long id;

	private String email;

	private String phone;

	private String description;

	private int zipCode;


}
