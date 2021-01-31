package com.financemanager.demo.site.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.financemanager.demo.site.dto.ItemDto;

@Component
public interface ItemService {
	ItemDto saveItem(ItemDto itemDto);

    void deleteItem(Integer itemId);

    List<ItemDto> findAll();
    
    List<ItemDto> findByCategoryIdInAndUserIdIn (int categoryId, int userId);
}
