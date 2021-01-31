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
		log.info("Handling find all caegories request");
		return itemService.findAll();
	}
	
	@PostMapping("/save")
    public ItemDto saveItem(@RequestBody ItemDto itemDto)  {
        log.info("Handling save item: " + itemDto);
        return itemService.saveItem(itemDto);
    }
	@DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Integer id) {
        log.info("Handling delete item request: " + id);
        itemService.deleteItem(id);
        return ResponseEntity.ok().build();
    }
	
	@GetMapping("/find?CategoryId={category_id}&UserId={user_id}")
    public List<ItemDto> findByCategoryIdInAndUserIdIn(@PathVariable Integer category_id, Integer user_id) {
        log.info("Handling find item by category id=" + category_id + ", and user id=" + user_id);
        return itemService.findByCategoryIdInAndUserIdIn(category_id, user_id);
    }
}
