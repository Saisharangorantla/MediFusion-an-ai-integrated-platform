package com.yourorg.telemedicine.dto;




public class AuthResponse{

	private final String jwttoken;
    private String role;
    
	public AuthResponse(String jwttoken) {
		this.jwttoken = jwttoken;
	}
	
	public AuthResponse(String jwttoken, String role) {
		this.jwttoken = jwttoken;
		this.role = role;
	}


	public String getToken() {
		return this.jwttoken;
	}

	public String getRole() {
		return role;
	}

	
}
