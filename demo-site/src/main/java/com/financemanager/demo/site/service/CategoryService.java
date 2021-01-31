package com.financemanager.demo.site.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.financemanager.demo.site.dto.CategoryDto;

@Component
public interface CategoryService {
	CategoryDto saveCategory(CategoryDto categoryDto);

    void deleteCategory(Integer catId);

    List<CategoryDto> findAll();
}
