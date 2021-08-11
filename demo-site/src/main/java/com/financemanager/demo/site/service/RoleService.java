package com.financemanager.demo.site.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.financemanager.demo.site.entity.Role;
import com.financemanager.demo.site.exception.ResourceNotFoundException;

@Component
public interface RoleService {

	Role saveRole(Role role);

    void deleteRole(Integer catId);

    List<Role> findAll();

	Role findRoleById(Integer roleId) throws ResourceNotFoundException;

	Optional<Role> findByName(String name);

}
