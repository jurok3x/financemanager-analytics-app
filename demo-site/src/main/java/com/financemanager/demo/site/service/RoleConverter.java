package com.financemanager.demo.site.service;

import org.springframework.stereotype.Component;

import com.financemanager.demo.site.dto.RoleDto;
import com.financemanager.demo.site.entity.Role;

@Component
public class RoleConverter {

	public Role fromRoleDtoToRole(RoleDto roleDto) {
		Role role = new Role();
		role.setId(roleDto.getId());
		role.setName(roleDto.getName());
        return role;
    }
	public RoleDto fromRoleToRoleDto(Role role) {
        return RoleDto.builder()
        		.id(role.getId())
        		.name(role.getName())
        		.build();
    }
}
