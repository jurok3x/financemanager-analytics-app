package com.financemanager.demo.site.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.financemanager.demo.site.dto.ItemDto;

@Component
public interface ItemService {
	ItemDto saveItem(ItemDto itemDto);

    void deleteItem(Integer itemId);

    List<ItemDto> findAll();
    
    List<ItemDto> findByCategoryId (int categoryId);

    List<ItemDto> getCurrentUserItems();
    
    List<ItemDto> getSpecifiedUserItems(int year, int month, Optional<String> sort, Optional<Boolean> isReversed);
    
    List<ItemDto> saveItemsFromExelFile(String path);
    
    Integer countItemsByCategory(int cetegoryId, int year, int month);
}
