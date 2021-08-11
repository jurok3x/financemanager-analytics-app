package com.financemanager.demo.site.controller;

import java.net.URI;
import java.util.List;

import javax.validation.constraints.Min;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.financemanager.demo.site.dto.UserDto;
import com.financemanager.demo.site.entity.User;
import com.financemanager.demo.site.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
@Log
public class UserController {

	private final UserService userService;

	@PostMapping
	public ResponseEntity<?> saveUser(@RequestBody @Validated User user) {
		log.info("Handling save user: " + user);
		User addedUser = userService.saveUser(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(addedUser.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}

	@GetMapping
	public List<UserDto> findAllUsers() {
		log.info("Handling find all users request");
		return userService.findAll();
	}

	@GetMapping("/user")
	public UserDto findByLogin(@RequestParam(required = true) String login) {
		log.info("Handling find with login = " + login);
		return userService.findByLogin(login);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
		log.info("Handling delete user request with id = " + id);
		UserDto deletedUser = userService.findById(id);
		userService.deleteUser(deletedUser.getId());
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/role/{id}")
	public List<UserDto> findByRoleId(@RequestParam @Min(value = 1, message = "Id should be greater than 1") Integer id) {
		log.info("Handling find all users with role = " + id + " request");
		return userService.findByRoleId(id);
	}

}
