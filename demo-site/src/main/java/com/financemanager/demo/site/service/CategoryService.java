package com.financemanager.demo.site.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.financemanager.demo.site.entity.Category;
import com.financemanager.demo.site.exception.NoSuchCategoryException;

@Component
public interface CategoryService {
	Category saveCategory(Category category);

    void deleteCategory(Integer catId);

    List<Category> findAll();
    
    Category findById(Integer catId) throws NoSuchCategoryException;
}
