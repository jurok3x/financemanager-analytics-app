package com.yurii.financeanalytics.entity;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

	private int id;
	
	@NotEmpty(message = "Name must not be empty")
	private String name;
	
	@NotEmpty(message = "Password must not be empty")
	private String password;
	
	@NotEmpty(message = "Email must not be empty")
    @Email(message = "Email must be a valid email address")
	private String email;
	
	@NotEmpty(message = "Role must not be empty")
	private Role role;
	
}
