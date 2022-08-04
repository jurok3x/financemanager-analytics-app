package com.yurii.financeanalytics.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.yurii.financeanalytics.dao.UserDao;
import com.yurii.financeanalytics.entity.CustomUserDetails;
import com.yurii.financeanalytics.entity.User;
import com.yurii.financeanalytics.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@PropertySource(value = {"classpath:/messages/errors.properties"})
public class CustomUserDetailsService implements UserDetailsService {

    private final UserDao userDao;
    
    @Value("email_not_found.error")
    private String emailNotFoundError;
	
	@Override
	public CustomUserDetails loadUserByUsername(String username) throws ResourceNotFoundException{
		User user = userDao.findByEmail(username).orElseThrow(
				()->new ResourceNotFoundException(String.format(emailNotFoundError, username)));
		return CustomUserDetails.fromUserToCustomUserDetails(user);	
	}

}
