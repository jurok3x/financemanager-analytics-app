package com.financemanager.demo.site.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.financemanager.demo.site.entity.Role;

@Component
public interface RoleService {

	Role saveRole(Role role);

    void deleteRole(Integer catId);

    List<Role> findAll();
    
    Role findByName(String name);
}
