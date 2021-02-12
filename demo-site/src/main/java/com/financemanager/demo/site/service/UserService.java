package com.financemanager.demo.site.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.financemanager.demo.site.dto.UserDto;
import com.financemanager.demo.site.exception.ValidationException;

@Component
public interface UserService {
	UserDto saveUser(UserDto usersDto) throws ValidationException;

    void deleteUser(Integer userId);

    UserDto findByLogin(String login);

    List<UserDto> findAll();
    
    List<UserDto> findByRoleId(Integer id);
}
