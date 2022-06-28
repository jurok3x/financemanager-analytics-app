package com.yurii.financeanalytics.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.yurii.financeanalytics.dao.UserDao;
import com.yurii.financeanalytics.entity.User;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class DefaultUserService implements UserService{

	private UserDao userDao;
	
	@Override
	public Optional<User> findByEmail(String email){
		return userDao.findByEmail(email);
	}

}
