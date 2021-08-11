package com.financemanager.demo.site.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.financemanager.demo.site.dto.UserDto;
import com.financemanager.demo.site.entity.Role;
import com.financemanager.demo.site.entity.User;
import com.financemanager.demo.site.exception.ResourceNotFoundException;
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
	public User saveUser(User user) {
		Role role = roleService.findByName("ROLE_USER").orElseThrow(
				()->new ResourceNotFoundException("Role with Name ROLE_USER Not Found!"));
		user.setRole(role);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}
	
	@Override
	public UserDto findById(Integer id) throws ResourceNotFoundException{
		User user = userRepository.findById(id).orElseThrow(
				()->new ResourceNotFoundException("User with ID :" + id +" Not Found!"));	
		return userConverter.fromUserToUserDto(user);
	}

	@Override
	public void deleteUser(Integer userId) {
		userRepository.deleteById(userId);		
	}

	@Override
	public UserDto findByLogin(String login) throws ResourceNotFoundException{
		User user = userRepository.findByLogin(login).orElseThrow(
				()->new ResourceNotFoundException("User with Login :" + login +" Not Found!"));
		return userConverter.fromUserToUserDto(user);
	}

	@Override
	public List<UserDto> findAll() {
		return userRepository.findAll()
                .stream()
                .map(userConverter::fromUserToUserDto)
                .collect(Collectors.toList());
	}

	@Override
	public List<UserDto> findByRoleId(Integer id) {
		return  userRepository.findByRoleId(id)
                .stream()
                .map(userConverter::fromUserToUserDto)
                .collect(Collectors.toList());
	}

	@Override
	public User findByLoginAndPassword(String login, String password) {
		Optional<User> user = userRepository.findByLogin(login);
		if (user.isPresent()) {
			if(passwordEncoder.matches(password, user.get().getPassword()))
				return user.get();
        }
		return null;
	}
	
	@Override
	public User getContextUser() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username;
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}
		return userRepository.findByLogin(username).orElseThrow(
				()->new ResourceNotFoundException("User with Name :" + username +" Not Found!"));
	}

}
