package com.yurii.financeanalytics.service.impl;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.yurii.financeanalytics.dao.UserDao;
import com.yurii.financeanalytics.entity.CustomUserDetails;
import com.yurii.financeanalytics.entity.User;
import com.yurii.financeanalytics.exception.ResourceNotFoundException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private UserDao userDao;
	
	@Override
	public CustomUserDetails loadUserByUsername(String username) throws ResourceNotFoundException{
		User user = userDao.findByEmail(username).orElseThrow(
				()->new ResourceNotFoundException(String.format("User with email %s Not Found!", username)));
		return CustomUserDetails.fromUserToCustomUserDetails(user);	
	}

}
