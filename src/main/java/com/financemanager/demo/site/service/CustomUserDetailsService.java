package com.financemanager.demo.site.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import com.financemanager.demo.site.entity.CustomUserDetails;
import com.financemanager.demo.site.entity.User;
import com.financemanager.demo.site.exception.ResourceNotFoundException;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private UserService userService;
	
	@Override
	public CustomUserDetails loadUserByUsername(String username) throws ResourceNotFoundException{
		User user = userService.findByEmail(username).orElseThrow(
				()->new ResourceNotFoundException(String.format("User with email %s Not Found!", username)));
		return CustomUserDetails.fromUserToCustomUserDetails(user);	
	}

}
