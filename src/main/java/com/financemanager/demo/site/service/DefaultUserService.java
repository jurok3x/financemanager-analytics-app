package com.financemanager.demo.site.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.financemanager.dao.UserDao;
import com.financemanager.demo.site.entity.User;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class DefaultUserService implements UserService{

	private final UserDao userDao;
	
	@Override
	public Optional<User> findByEmail(String email){
		return userDao.findByEmail(email);
	}

}
