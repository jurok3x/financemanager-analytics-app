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
import com.financemanager.demo.site.dto.CategoryDto;
import com.financemanager.demo.site.service.CategoryService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;

@RestController
@RequestMapping("/category")
@AllArgsConstructor
@Log
public class CategoryController {
	
	private final CategoryService categoryService;
	
	@GetMapping("/findAll")
	public List<CategoryDto> findAllCategories() {
		log.info("Handling find all caegories request");
		return categoryService.findAll();
	}
	@PostMapping("/save")
    public CategoryDto saveCategory(@RequestBody CategoryDto categoryDto) {
        log.info("Handling save category: " + categoryDto);
        return categoryService.saveCategory(categoryDto);
    }
	@DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Integer id) {
        log.info("Handling delete category request: " + id);
        categoryService.deleteCategory(id);
        return ResponseEntity.ok().build();
    }
}
