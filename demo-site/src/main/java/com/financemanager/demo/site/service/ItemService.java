package com.financemanager.demo.site.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.financemanager.demo.site.dto.ItemDto;
import com.financemanager.demo.site.entity.projects.DatePartAndCost;
import com.financemanager.demo.site.entity.projects.ProjectNameAndCountAndCost;

@Component
public interface ItemService {
	ItemDto saveItem(ItemDto itemDto);

    void deleteItem(Integer itemId);

    List<ItemDto> findAll(Optional<String> year, Optional<String> month, Optional<Integer> limit, Optional<Integer> offset);
    
    List<ItemDto> findByCategory(int categoryId, Optional<String> year, Optional<String> month, Optional<Integer> limit, Optional<Integer> offset);
    
    Integer countItemsByCategory(int cetegoryId, Optional<String> year, Optional<String> month);
    
    List<ProjectNameAndCountAndCost>getMostFrequentItems(Optional<Integer> categoryId, 
    		Optional<String> year, Optional<String> month, Optional<Integer> limit, Optional<Integer> offset);
    
    List<Integer> getAllYears();
    
    List<DatePartAndCost> getMonthStatistics(Optional<Integer> categoryId, Optional<String> year);
}
