package com.financemanager.demo.site.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.financemanager.demo.site.dto.ItemDto;
import com.financemanager.demo.site.entity.Item;
import com.financemanager.demo.site.repository.ItemRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class DefaultItemService implements ItemService{
	private final ItemRepository itemRepository;
	private final ItemConverter itemConverter;
	@Override
	public ItemDto saveItem(ItemDto itemDto) {
		SecurityContextHolder.getContext().getAuthentication().getName();
		Item savedItem = itemRepository.save(itemConverter.fromItemDtoToItem(itemDto));
		return itemConverter.fromItemToItemDto(savedItem);
	}

	@Override
	public void deleteItem(Integer itemId) {
		itemRepository.deleteById(itemId);
	}

	@Override
	public List<ItemDto> findAll() {
		return itemRepository.findAll()
				.stream()
				.map(itemConverter::fromItemToItemDto)
				.collect(Collectors.toList());
	}

	@Override
	public List<ItemDto> findByCategoryIdInAndUserIdIn(int categoryId, int userId) {
		return itemRepository.findByCategoryIdAndUserId(categoryId, userId)
				.stream()
				.map(itemConverter::fromItemToItemDto)
				.collect(Collectors.toList());
	}

}
