package com.financemanager.demo.site.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.financemanager.demo.site.dto.GroupDto;
import com.financemanager.demo.site.entity.Group;
import com.financemanager.demo.site.repository.GroupRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DefaultGroupService implements GroupService {

	private final GroupRepository groupRepository;
	private final GroupConverter groupConverter;
	
	@Override
	public GroupDto saveGroup(GroupDto groupDto) {
		Group savedGroup = groupRepository.save(groupConverter.fromGroupDtoToGroup(groupDto));
		return groupConverter.fromGroupToGroupDto(savedGroup);
	}

	@Override
	public void deleteGroup(Integer groupId) {
		groupRepository.deleteById(groupId);
	}

	@Override
	public List<GroupDto> findAll() {
		return groupRepository.findAll()
				.stream()
				.map(groupConverter::fromGroupToGroupDto)
				.collect(Collectors.toList());
	}

}
