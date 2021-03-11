package com.financemanager.demo.site.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.financemanager.demo.site.dto.UserDto;
import com.financemanager.demo.site.entity.User;
import com.financemanager.demo.site.repository.UserRepository;

@Component
public class UserConverter {
	@Autowired
	private UserRepository userRepository;
	
	public User fromUserDtoToUser(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setEmail(userDto.getEmail());
        user.setName(userDto.getName());
        user.setLogin(userDto.getLogin());
        user.setPassword(userRepository.findByLogin(userDto.getLogin()).getPassword());
        user.setRole(userDto.getRole());
        return user;
    }
	
	public UserDto fromUserToUserDto(User user) {
		 return UserDto.builder()
				 .id(user.getId())
				 .login(user.getLogin())
				 .email(user.getEmail())
				 .name(user.getName())
				 .role(user.getRole())
				 .build();
	}
}
