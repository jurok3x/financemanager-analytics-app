package com.financemanager.demo.site.dto;


import java.util.Date;

import com.financemanager.demo.site.entity.Category;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Builder
@Data
@Getter
@Setter
public class ItemDto {
	private int id;
	private String name;
	private double price;
	private Date date;
	private Category category;
	private UserDto userDto;
}
