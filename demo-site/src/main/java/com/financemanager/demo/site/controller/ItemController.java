package com.financemanager.demo.site.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.financemanager.demo.site.dto.ItemDto;
import com.financemanager.demo.site.entity.projects.DatePartAndCost;
import com.financemanager.demo.site.entity.projects.ProjectNameAndCountAndCost;
import com.financemanager.demo.site.service.ItemService;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;

@RestController
@RequestMapping("/items")
@Validated
@AllArgsConstructor
@Log
public class ItemController {
	private ItemService itemService;

	@PostMapping
	public ItemDto saveItem(@RequestBody ItemDto itemDto) {
		log.info("Handling save item: " + itemDto);
		return itemService.saveItem(itemDto);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteItem(@PathVariable Integer id) {
		log.info("Handling delete item request: " + id);
		itemService.deleteItem(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping
	public List<ItemDto> findAll(
			@RequestParam Optional<String> year,
			@RequestParam Optional<String> month,
			@RequestParam Optional<Integer> limit,
			@RequestParam Optional<Integer> offset) {
		log.info("Handling find items with year = " + year.orElse("All") + " and month = " + month.orElse("All"));
		return itemService.findAll(year, month, limit, offset);
	}
	
	@GetMapping(value = {"/category/{categoryId}"})
	public List<ItemDto> findByCategoryId(
			@PathVariable @Min(value = 1, message = "CategoryId must be greater than or equal to 1") 
				@Max(value = 10, message = "CategoryId must be greater than or equal to 10") Integer categoryId,
			@RequestParam Optional<String> year,
			@RequestParam Optional<String> month,
			@RequestParam Optional<Integer> limit,
			@RequestParam Optional<Integer> offset) {
		log.info("Handling find items in category  with id =  " + categoryId + ". With year = " + year.orElse("All") + " and month = " + month.orElse("All"));
		return itemService.findByCategory(categoryId, year, month, limit, offset);
	}

	@GetMapping("/count/category/{categoryId}")
	public Integer countByCategoryAndDate(
			@PathVariable @Min(value = 1, message = "CategoryId must be greater than or equal to 1")
				@Max(value = 10, message = "CategoryId must be greater than or equal to 10")Integer categoryId,
			@RequestParam Optional<String> year,
			@RequestParam Optional<String> month) {
		log.info("Handling get items count in category  with id = " + categoryId + ". With year = " + year.orElse("All") + " and month = " + month.orElse("All"));
		return itemService.countItemsByCategory(categoryId, year, month);
	}
	
	
	@GetMapping("/popular")
	public List<ProjectNameAndCountAndCost> getMostFrequentItems(
			@RequestParam Optional<@Min(value = 1, message = "CategoryId must be greater than or equal to 1")
			@Max(value = 10, message = "CategoryId must be greater than or equal to 10")Integer> categoryId,
			@RequestParam Optional<String> year,
			@RequestParam Optional<String> month,
			@RequestParam Optional<Integer> limit,
			@RequestParam Optional<Integer> offset) {
		log.info("Handling find most popular items. With year = " + year.orElse("All") + " and month = " + month.orElse("All"));
		return itemService.getMostFrequentItems(categoryId, year, month, limit, offset);
	}
	
	@GetMapping("/years")
	public List<Integer> getAllYears() {
		log.info("Handling find all years.");
		return itemService.getAllYears();
	}
	
	@GetMapping("/statistics")
	
	public List<DatePartAndCost> getMonthStatistics(
			@RequestParam Optional<@Min(value = 1, message = "CategoryId must be greater than or equal to 1")
				@Max(value = 10, message = "CategoryId must be greater than or equal to 10")Integer> categoryId,
			@RequestParam Optional<String> year) {
		log.info("Handling get month statistics. With year = " + year.orElse("All") + "and categoryId = "
			+ ((categoryId.isPresent()) ? categoryId.get() : "All"));
		return itemService.getMonthStatistics(categoryId, year);
	}
}
