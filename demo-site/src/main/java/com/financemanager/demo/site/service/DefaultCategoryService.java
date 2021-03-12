package com.financemanager.demo.site.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.financemanager.demo.site.entity.Category;
import com.financemanager.demo.site.exception.NoSuchCategoryException;
import com.financemanager.demo.site.repository.CategoryRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class DefaultCategoryService implements CategoryService{
	private final CategoryRepository categoryRepository;
	
@Override
public Category saveCategory(Category category) {
	return categoryRepository.save(category);
}

@Override
public void deleteCategory(Integer catId) {
	categoryRepository.deleteById(catId);
}

@Override
public List<Category> findAll() {
	return categoryRepository.findAll()
			.stream()
			.collect(Collectors.toList());
}

@Override
public Category findById(Integer id) throws NoSuchCategoryException {
	// TODO Auto-generated method stub
	Category category = categoryRepository.findById(id)
			.orElseThrow(() -> new NoSuchCategoryException("Category not found"));
	return category;
}


}
