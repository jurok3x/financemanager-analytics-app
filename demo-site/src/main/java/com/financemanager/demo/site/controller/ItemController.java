package com.financemanager.demo.site.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.financemanager.demo.site.dto.ItemDto;
import com.financemanager.demo.site.service.ItemService;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;

@RestController
@RequestMapping("/item")
@AllArgsConstructor
@Log
public class ItemController {
	private ItemService itemService;

	@PostMapping("/save")
	public ItemDto saveItem(@RequestBody ItemDto itemDto) {
		log.info("Handling save item: " + itemDto);
		return itemService.saveItem(itemDto);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteItem(@PathVariable Integer id) {
		log.info("Handling delete item request: " + id);
		itemService.deleteItem(id);
		return ResponseEntity.ok().build();
	}

	@GetMapping(value = {"/findAll"})
	public List<ItemDto> findAll(@RequestParam Optional<Integer> year, @RequestParam Optional<Integer> month,
			@RequestParam(name = "sortBy") Optional<String> sort, @RequestParam(name = "reversed") Optional<Boolean> isReversed) {
		log.info("Handling find items with " + ((month.isPresent()) ? "month = " + (month.get() + 1) : "") + ((year.isPresent()) ? " and year = " + year.get() : "") +
				((sort.isPresent()) ? " sorted by = " + sort.get() : "") + ((isReversed.isPresent()) ? " reverse = " + isReversed.get() : ""));
		return itemService.findAll(year, month, sort, isReversed);
	}
	
	@GetMapping(value = {"/findByCategoryId/{categoryId}"})
	public List<ItemDto> findByCategoryId(@PathVariable Integer categoryId, @RequestParam Optional<Integer> year, @RequestParam Optional<Integer> month,
			@RequestParam(name = "sortBy") Optional<String> sort, @RequestParam(name = "reversed") Optional<Boolean> isReversed) {
		log.info("Handling find items with categoryId = " + categoryId + ((month.isPresent()) ? "month = " + (month.get() + 1) : "") + ((year.isPresent()) ? " and year = " + year.get() : "") +
				((sort.isPresent()) ? " sorted by = " + sort.get() : "") + ((isReversed.isPresent()) ? " reverse = " + isReversed.get() : ""));
		return itemService.findByCategory(categoryId, year, month, sort, isReversed);
	}

	@GetMapping("/itemsCount/{categoryId}")
	public Integer countByCategoryAndDate(@PathVariable Integer categoryId, @RequestParam Optional<Integer> year
			, @RequestParam Optional<Integer> month) {
		log.info("Handling get items count in category  with id = " + categoryId + ((month.isPresent()) ? " and month = " + (month.get() + 1) : "") +
				((year.isPresent()) ? " and year = " + year.get() : ""));
		return itemService.countItemsByCategory(categoryId, year, month);
	}
	
	@GetMapping("/saveFromExelFile")
	public List<ItemDto> saveFromExelFile() {
		log.info("Handling save multiple items from exel file.");
		return itemService.saveItemsFromExelFile("E:\\items.xls");
	}
	
}
