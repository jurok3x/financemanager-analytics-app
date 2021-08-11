package com.financemanager.demo.site.dto;


import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

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
	@NotEmpty(message = "Name must not be empty")
	private String name;
	@NotEmpty(message = "Price must not be empty")
	@Min(value = 0, message = "Price must be greater than or equal to 0")
	private double price;
	@NotEmpty(message = "Date must not be empty")
	private Date date;
	@NotEmpty(message = "Category must not be empty")
	private Category category;
	@NotEmpty(message = "User must not be empty")
	private UserDto userDto;
}
