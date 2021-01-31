package com.financemanager.demo.site.service;

import org.springframework.stereotype.Component;

import com.financemanager.demo.site.dto.CategoryDto;
import com.financemanager.demo.site.entity.Category;

@Component
public class CategoryConverter {
	public Category fromCategoryDtoToCategory(CategoryDto categoryDto) {
		Category category = new Category();
		category.setId(categoryDto.getId());
		category.setName(categoryDto.getName());
        return category;
    }
	public CategoryDto fromCategoryToCategoryDto(Category category) {
        return CategoryDto.builder()
        		.id(category.getId())
        		.name(category.getName())
        		.build();
    }
}
