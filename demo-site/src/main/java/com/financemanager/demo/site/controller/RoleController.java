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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.financemanager.demo.site.entity.Role;
import com.financemanager.demo.site.service.RoleService;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;

@RestController
@RequestMapping("/api/roles")
@AllArgsConstructor
@Log
public class RoleController {

	private final RoleService roleService;
	
	@GetMapping
	public List<Role> findAllRoles() {
		log.info("Handling find all roles request");
		return roleService.findAll();
	}
	
	@GetMapping("/{id}")
	public Role findRoleById(@PathVariable 
			@Min(value = 1, message = "Id should be greater than 1") Integer id) {
		log.info("Handling find all roles request");
		return roleService.findRoleById(id);
	}
	
	@PostMapping
    public ResponseEntity<?> saveRole(@Validated @RequestBody Role role) {
        log.info("Handling save role: " + role);
        Role addedRole = roleService.saveRole(role);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(addedRole.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
	
	@DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable
    		@Min(value = 1, message = "Id should be greater than 1") Integer id) {
        log.info("Handling delete role with id = " + id);
        Role role = roleService.findRoleById(id);
        roleService.deleteRole(role.getId());
        return ResponseEntity.noContent().build();
    }
}
