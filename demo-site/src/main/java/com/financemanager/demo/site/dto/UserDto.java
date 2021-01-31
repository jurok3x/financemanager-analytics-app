package com.financemanager.demo.site.dto;

import com.financemanager.demo.site.entity.Group;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Builder
@Data
@Getter
@Setter
public class UserDto {
	private int id;
	private String name;
	private String login;
	private String password;
	private String email;
	private Group group;
}
