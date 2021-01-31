package com.financemanager.demo.site.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.financemanager.demo.site.dto.GroupDto;

@Component
public interface GroupService {

	GroupDto saveGroup(GroupDto groupDto);

    void deleteGroup(Integer catId);

    List<GroupDto> findAll();
}
