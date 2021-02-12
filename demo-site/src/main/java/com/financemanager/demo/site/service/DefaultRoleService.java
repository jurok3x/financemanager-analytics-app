package com.financemanager.demo.site.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.financemanager.demo.site.dto.RoleDto;
import com.financemanager.demo.site.entity.Role;
import com.financemanager.demo.site.repository.RoleRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DefaultRoleService implements RoleService {

	private final RoleRepository roleRepository;
	private final RoleConverter roleConverter;
	
	@Override
	public RoleDto saveRole(RoleDto roleDto) {
		Role savedGroup = roleRepository.save(roleConverter.fromRoleDtoToRole(roleDto));
		return roleConverter.fromRoleToRoleDto(savedGroup);
	}

	@Override
	public void deleteRole(Integer groupId) {
		roleRepository.deleteById(groupId);
	}

	@Override
	public List<RoleDto> findAll() {
		return roleRepository.findAll()
				.stream()
				.map(roleConverter::fromRoleToRoleDto)
				.collect(Collectors.toList());
	}

	@Override
	public RoleDto findByName(String name) {
		// TODO Auto-generated method stub
		return roleConverter.fromRoleToRoleDto(roleRepository.findByName(name));
	}

}
