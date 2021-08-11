package com.financemanager.demo.site.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.financemanager.demo.site.entity.Category;
import com.financemanager.demo.site.entity.projects.ProjectCategoryAndCost;
import com.financemanager.demo.site.service.CategoryService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;

@RestController
@RequestMapping("/api/categories")
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
    public ResponseEntity<?> saveCategory(@Valid @RequestBody Category category) {
        log.info("Handling save category: " + category);
        Category addedCategory = categoryService.saveCategory(category);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(addedCategory.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
	
	@DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable
    		@Min(value = 1, message = "Id should be greater than 1") Integer id) {
        log.info("Handling delete category request: " + id);
        Category category = categoryService.findById(id);
        categoryService.deleteCategory(category.getId());
        return ResponseEntity.noContent().build();
    }
	
	@GetMapping("/{id}")
	public Category findCategoryById(@PathVariable Integer id) {
		log.info("Handling find caegory with id = " + id);
		return categoryService.findById(id);
	}
	
	@GetMapping("/cost")
	public List<ProjectCategoryAndCost> getCategoriesAndCost(
			@RequestParam Optional<@Pattern(regexp = "^[0-9]{4}", message = "Incorect year") String> year,
			@RequestParam Optional<@Pattern(regexp = "^[1-12]{1}", message = "Incorect month") String> month) {
		log.info("Handling find caegories with their cost");
		return categoryService.getCategoriesAndCost(year, month);
	}
}
