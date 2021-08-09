package com.financemanager.demo.site.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.financemanager.demo.site.entity.Role;
import com.financemanager.demo.site.repository.RoleRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DefaultRoleService implements RoleService {

	private final RoleRepository roleRepository;
	
	@Override
	public Role saveRole(Role role) {
		return roleRepository.save(role);
		
	}

	@Override
	public void deleteRole(Integer groupId) {
		roleRepository.deleteById(groupId);
	}

	@Override
	public List<Role> findAll() {
		return roleRepository.findAll()
				.stream()
				.collect(Collectors.toList());
	}

	@Override
	public Role findByName(String name) throws IllegalStateException {
		Role role = roleRepository.findByName(name).orElseThrow(() -> new IllegalStateException("Role not found."));
		return role;
	}

}
