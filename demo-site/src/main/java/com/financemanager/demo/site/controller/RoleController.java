package com.financemanager.demo.site.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.financemanager.demo.site.entity.Role;
import com.financemanager.demo.site.service.RoleService;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;

@RestController
@RequestMapping("/roles")
@AllArgsConstructor
@Log
public class RoleController {

	private final RoleService roleService;
	
	@GetMapping
	public List<Role> findAllRoles() {
		log.info("Handling find all groups request");
		return roleService.findAll();
	}
	
	@GetMapping("/{name}")
	public Role findRoleByName(@PathVariable String name) {
		log.info("Handling find all groups request");
		try {
			return roleService.findByName(name);
		} catch (IllegalStateException e) {		
			log.info("Role not found.");
			throw new IllegalStateException("Role not found.");
		}
	}
	
	@PostMapping
    public Role saveRole(@RequestBody Role role) {
        log.info("Handling save group: " + role);
        return roleService.saveRole(role);
    }
	@DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Integer id) {
        log.info("Handling delete group request: " + id);
        roleService.deleteRole(id);
        return ResponseEntity.ok().build();
    }
}
