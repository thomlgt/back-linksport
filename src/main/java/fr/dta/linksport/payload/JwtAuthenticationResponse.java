package fr.dta.linksport.payload;


import java.util.Optional;

import fr.dta.linksport.domain.User;



public class JwtAuthenticationResponse {
	private Optional<User> user;
	private String accessToken;
	private String tokenType = "Bearer";
	public JwtAuthenticationResponse(String accessToken, Optional<User> optional) {
		this.accessToken = accessToken;
		this.user = optional;
	}
	public String getAccessToken() {
		return this.accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getTokenType() {
		return this.tokenType;
	}
	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}
	public Optional<User> getUser() {
		return this.user;
	}
	public void setUser(Optional<User> user) {
		this.user = user;
	}
}