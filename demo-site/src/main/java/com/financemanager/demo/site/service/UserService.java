package com.financemanager.demo.site.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.financemanager.demo.site.dto.UserDto;
import com.financemanager.demo.site.entity.User;
import com.financemanager.demo.site.exception.ResourceNotFoundException;

@Component
public interface UserService {
	User saveUser(User user);

    void deleteUser(Integer userId);

    UserDto findByLogin(String login) throws ResourceNotFoundException;
    
    UserDto findById(Integer id) throws ResourceNotFoundException;
    
    User findByLoginAndPassword(String login, String password);

    List<UserDto> findAll();
    
    List<UserDto> findByRoleId(Integer id);
    
    User getContextUser();
}
