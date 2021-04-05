package com.financemanager.demo.site.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.financemanager.demo.site.dto.ItemDto;
import com.financemanager.demo.site.entity.Item;

@Component
public class ItemConverter {
	
	@Autowired
	private UserConverter userConverter;
	
	public Item fromItemDtoToItem(ItemDto itemDto) {
		Item item = new Item();
		item.setId(itemDto.getId());
		item.setPrice(itemDto.getPrice());
		item.setName(itemDto.getName());
		item.setDate(itemDto.getDate());
		item.setCategory(itemDto.getCategory());
		item.setUser(userConverter.fromUserDtoToUser(itemDto.getUserDto()));
        return item;
    }
	public ItemDto fromItemToItemDto(Item item) {
        return ItemDto.builder()
        		.price(item.getPrice())
        		.id(item.getId())
        		.name(item.getName())
        		.date(item.getDate())
        		.category(item.getCategory())
        		.userDto(userConverter.fromUserToUserDto(item.getUser()))
        		.build();
    }

}
