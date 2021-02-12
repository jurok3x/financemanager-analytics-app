package com.financemanager.demo.site.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.financemanager.demo.site.dto.RoleDto;

@Component
public interface RoleService {

	RoleDto saveRole(RoleDto roleDto);

    void deleteRole(Integer catId);

    List<RoleDto> findAll();
    
    RoleDto findByName(String name);
}
