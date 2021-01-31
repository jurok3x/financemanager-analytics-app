package com.financemanager.demo.site.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.financemanager.demo.site.dto.CategoryDto;
import com.financemanager.demo.site.entity.Category;
import com.financemanager.demo.site.repository.CategoryRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class DefaultCategoryService implements CategoryService{
	private final CategoryRepository categoryRepository;
	private final CategoryConverter categoryConverter;
@Override
public CategoryDto saveCategory(CategoryDto categoryDto) {
	Category savedCategory = categoryRepository.save(categoryConverter.fromCategoryDtoToCategory(categoryDto));
	return categoryConverter.fromCategoryToCategoryDto(savedCategory);
}

@Override
public void deleteCategory(Integer catId) {
	categoryRepository.deleteById(catId);
}

@Override
public List<CategoryDto> findAll() {
	return categoryRepository.findAll()
			.stream()
			.map(categoryConverter::fromCategoryToCategoryDto)
			.collect(Collectors.toList());
}


}
