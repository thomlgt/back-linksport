package fr.dta.linksport.model;

import java.io.Serializable;

import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangePassword implements Serializable{
	
	@Size(min = 3)
	private String currentPassword;
	
	@Size(min = 3)
	private String password;

}
