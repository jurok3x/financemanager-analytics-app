package com.financemanager.demo.site.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.financemanager.demo.site.dto.UserDto;
import com.financemanager.demo.site.entity.User;
import com.financemanager.demo.site.exception.ValidationException;
import com.financemanager.demo.site.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
@Log
public class UserController {
	
	private final UserService userService;
	
	@PostMapping("/save")
    public User saveUser(@RequestBody User user) throws ValidationException {
		log.info("Handling save user: " + user);
        return userService.saveUser(user);
    }
	
	@GetMapping("/findAll")
    public List<UserDto> findAllUsers() {
        log.info("Handling find all users request");
        return userService.findAll();
    }
	@GetMapping("/findByLogin")
    public UserDto findByLogin(@RequestParam String login) {
        log.info("Handling find by login request: " + login);
        return userService.findByLogin(login);
    }
	@DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        log.info("Handling delete user request: " + id);
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
	@GetMapping("/findByRole/{id}")
    public List<UserDto> findByGroupId(@RequestParam Integer id) {
        log.info("Handling find all users by group request");
        return userService.findByRoleId(id);
    }
	
	@GetMapping("/fail")
    public void authFail() {
        log.info("Failed to authenticate");
        System.out.println("User not found!");;
    }
	
	@GetMapping("/admin/get")
    public String getAdmin() {
		return "Hi admin";
    }
	
	@GetMapping("/user/get")
    public String getUser() {
		
		

        return "Hi user ";
    }
	
}
