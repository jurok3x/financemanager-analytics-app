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

	@GetMapping("/findAll")
	public List<ItemDto> findAllItems() {
		log.info("Handling find all items request");
		return itemService.findAll();
	}

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

	@GetMapping("/findCategoryId/{categoryId}")
	public List<ItemDto> findByCategoryId(@PathVariable Integer categoryId) {
		log.info("Handling find item by category id = " + categoryId);
		return itemService.findByCategoryId(categoryId);
	}

	@GetMapping(value = {"/findDate/{year}/{month}"})
	public List<ItemDto> findByMonthAndYear(@PathVariable Integer month, @PathVariable Integer year,
			@RequestParam(name = "sortBy") Optional<String> sort, @RequestParam(name = "reversed") Optional<Boolean> isReversed) {
		month++;
		log.info("Handling find item with month = " + month + " and year = " + year + " sorted by " + sort + " reverse " + isReversed);
		return itemService.getSpecifiedUserItems(year, month, sort, isReversed);
	}

	@GetMapping("/findCurrentItems") // add date to string
	public List<ItemDto> findCurrentUserItems() {
		log.info("Handling find cuurent user items");
		return itemService.getCurrentUserItems();
	}

	@GetMapping("/saveFromExelFile")
	public List<ItemDto> saveFromExelFile() {
		log.info("Handling save multiple items: ");
		return itemService.saveItemsFromExelFile("E:\\items.xls");
	}
	
	@GetMapping("/itemsCount/{categoryId}")
	public Integer countByCategoryAndDate(@PathVariable Integer categoryId, @RequestParam int year
			, @RequestParam int month) {
		month++;
		log.info("Handling get category count of id =" + categoryId);
		return itemService.countItemsByCategory(categoryId, year, month);
	}
}
