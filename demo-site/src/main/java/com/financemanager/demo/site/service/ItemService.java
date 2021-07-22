package com.financemanager.demo.site.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.financemanager.demo.site.dto.ItemDto;
import com.financemanager.demo.site.entity.projects.ProjectNameAndCount;

@Component
public interface ItemService {
	ItemDto saveItem(ItemDto itemDto);

    void deleteItem(Integer itemId);

    List<ItemDto> findAll(Optional<Integer> year, Optional<Integer> month);
    
    List<ItemDto> findByCategory(int categoryId, Optional<Integer> year, Optional<Integer> month);
         
    List<ItemDto> saveItemsFromExelFile(String path);
    
    Integer countItemsByCategory(int cetegoryId, Optional<Integer> year, Optional<Integer> month);
    
    List<ProjectNameAndCount>getMostFrequentItems(Optional<Integer> categoryId);
}
