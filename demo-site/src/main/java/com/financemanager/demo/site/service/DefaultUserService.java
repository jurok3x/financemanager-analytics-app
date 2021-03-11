package com.financemanager.demo.site.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.financemanager.demo.site.dto.UserDto;
import com.financemanager.demo.site.entity.Role;
import com.financemanager.demo.site.entity.User;
import com.financemanager.demo.site.exception.ValidationException;
import com.financemanager.demo.site.repository.UserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class DefaultUserService implements UserService{

	private final UserRepository userRepository;
	private final UserConverter userConverter;
	private final RoleService roleService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Override
	public User saveUser(User user) throws ValidationException {
		validateUser(user);
		Role role = roleService.findByName("ROLE_USER");
		user.setRole(role);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	@Override
	public void deleteUser(Integer userId) {
		userRepository.deleteById(userId);		
	}

	@Override
	public UserDto findByLogin(String login) {
		User user = userRepository.findByLogin(login);
		if (user != null) {
            return userConverter.fromUserToUserDto(user);
        }
		return null;
	}
	
	@Override
	public User findByLoginAndPassword(String login, String password) {
		User user = userRepository.findByLogin(login);
		if (user != null) {
			if(passwordEncoder.matches(password, user.getPassword()))
				return user;
        }
		return null;
	}

	@Override
	public List<UserDto> findAll() {
		return userRepository.findAll()
                .stream()
                .map(userConverter::fromUserToUserDto)
                .collect(Collectors.toList());
	}
	private void validateUser(User user) throws ValidationException{
		if(user.equals(null)){
			throw new ValidationException("Object user is null");
		}
		if (user.getLogin().isEmpty() || user.getLogin().equals(null)){
			throw new ValidationException("Login is empty");
		}
	}

	@Override
	public List<UserDto> findByRoleId(Integer id) {
		return  userRepository.findByRoleId(id)
                .stream()
                .map(userConverter::fromUserToUserDto)
                .collect(Collectors.toList());
	}

}
