package com.financemanager.demo.site.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.financemanager.demo.site.entity.Category;
import com.financemanager.demo.site.exception.NoSuchCategoryException;
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
	public Category findCategoryById(@PathVariable Integer id) {
		log.info("Handling find caegory with id="+id);
		try {
			return categoryService.findById(id);
		} catch (NoSuchCategoryException e) {
			// TODO Auto-generated catch block
			log.info("Category not found.");
			return null;
		} 
	}
}
