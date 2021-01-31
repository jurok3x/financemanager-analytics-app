package com.financemanager.demo.site.service;

import org.springframework.stereotype.Component;

import com.financemanager.demo.site.dto.GroupDto;
import com.financemanager.demo.site.entity.Group;

@Component
public class GroupConverter {

	public Group fromGroupDtoToGroup(GroupDto groupDto) {
		Group group = new Group();
		group.setId(groupDto.getId());
		group.setName(groupDto.getName());
        return group;
    }
	public GroupDto fromGroupToGroupDto(Group group) {
        return GroupDto.builder()
        		.id(group.getId())
        		.name(group.getName())
        		.build();
    }
}
