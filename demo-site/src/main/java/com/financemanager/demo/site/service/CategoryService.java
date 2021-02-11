package com.financemanager.demo.site.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.financemanager.demo.site.dto.CategoryDto;
import com.financemanager.demo.site.exception.NoSuchCategoryException;

@Component
public interface CategoryService {
	CategoryDto saveCategory(CategoryDto categoryDto);

    void deleteCategory(Integer catId);

    List<CategoryDto> findAll();
    
    CategoryDto findById(Integer catId) throws NoSuchCategoryException;
}
