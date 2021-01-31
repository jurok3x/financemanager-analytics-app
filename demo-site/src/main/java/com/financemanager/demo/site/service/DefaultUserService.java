package com.financemanager.demo.site.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.financemanager.demo.site.dto.UserDto;
import com.financemanager.demo.site.entity.User;
import com.financemanager.demo.site.exception.ValidationException;
import com.financemanager.demo.site.repository.UserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class DefaultUserService implements UserService{

	private final UserRepository userRepository;
	private final UserConverter userConverter;
	@Override
	public UserDto saveUser(UserDto userDto) throws ValidationException {
		validateUserDto(userDto);
		User savedUser = userRepository.save(userConverter.fromUserDtoToUser(userDto));
		return userConverter.fromUserToUserDto(savedUser);
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
	public List<UserDto> findAll() {
		return userRepository.findAll()
                .stream()
                .map(userConverter::fromUserToUserDto)
                .collect(Collectors.toList());
	}
	private void validateUserDto(UserDto userDto) throws ValidationException{
		if(userDto.equals(null)){
			throw new ValidationException("Object user is null");
		}
		if (userDto.getLogin().isEmpty() || userDto.getLogin().equals(null)){
			throw new ValidationException("Login is empty");
		}
	}

	@Override
	public List<UserDto> findByGroupId(Integer id) {
		return  userRepository.findByGroupId(id)
                .stream()
                .map(userConverter::fromUserToUserDto)
                .collect(Collectors.toList());
	}

}
