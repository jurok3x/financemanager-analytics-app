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

import com.financemanager.demo.site.dto.GroupDto;
import com.financemanager.demo.site.service.GroupService;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;

@RestController
@RequestMapping("/group")
@AllArgsConstructor
@Log
public class GroupController {

	private final GroupService groupService;
	
	@GetMapping("/findAll")
	public List<GroupDto> findAllGroups() {
		log.info("Handling find all groups request");
		return groupService.findAll();
	}
	@PostMapping("/save")
    public GroupDto saveGroup(@RequestBody GroupDto groupDto) {
        log.info("Handling save group: " + groupDto);
        return groupService.saveGroup(groupDto);
    }
	@DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Integer id) {
        log.info("Handling delete group request: " + id);
        groupService.deleteGroup(id);
        return ResponseEntity.ok().build();
    }
}
