package com.financemanager.demo.site.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.financemanager.demo.site.entity.CustomUserDetails;
import com.financemanager.demo.site.entity.User;

@Component
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
    private UserService userService;
	
	@Autowired
    private UserConverter userConverter;
	
	@Override
	public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userConverter.fromUserDtoToUser(userService.findByLogin(username));
		System.out.println("Password is "+user.getPassword());
		return CustomUserDetails.fromUserToCustomUserDetails(user);
		
	}

}
