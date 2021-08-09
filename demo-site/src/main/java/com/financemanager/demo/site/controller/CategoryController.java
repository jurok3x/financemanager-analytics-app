package com.financemanager.demo.site.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.financemanager.demo.site.entity.Category;
import com.financemanager.demo.site.entity.projects.ProjectCategoryAndCost;
import com.financemanager.demo.site.exception.CategoryNotFoundException;
import com.financemanager.demo.site.service.CategoryService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;

@RestController
@RequestMapping("/categories")
@AllArgsConstructor
@Log
public class CategoryController {
	
	private final CategoryService categoryService;
	
	@GetMapping
	public List<Category> findAllCategories() {
		log.info("Handling find all caegories request");
		return categoryService.findAll();
	}
	
	@PostMapping
    public Category saveCategory(@RequestBody Category category) {
        log.info("Handling save category: " + category);
        return categoryService.saveCategory(category);
    }
	
	@DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Integer id) {
        log.info("Handling delete category request: " + id);
        categoryService.deleteCategory(id);
        return ResponseEntity.ok().build();
    }
	
	@GetMapping("/{id}")
	public Category findCategoryById(@PathVariable Integer id) throws CategoryNotFoundException {
		log.info("Handling find caegory with id = " + id);
		try {
			return categoryService.findById(id);
		} catch (CategoryNotFoundException ex) {
			
			log.info("Category not found.");
			throw new ResponseStatusException(
			          HttpStatus.NOT_FOUND, "Category Not Found", ex);
		} 
	}
	
	@GetMapping("/cost")
	public List<ProjectCategoryAndCost> getCategoriesAndCost(@RequestParam Optional<Integer> year,
			@RequestParam Optional<Integer> month) {
		log.info("Handling find caegories with their cost");
		return categoryService.getCategoriesAndCost(year, month);
	}
}
