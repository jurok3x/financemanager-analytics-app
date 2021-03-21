package com.financemanager.demo.site.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.financemanager.demo.site.dto.ItemDto;
import com.financemanager.demo.site.entity.CustomUserDetails;
import com.financemanager.demo.site.entity.Item;
import com.financemanager.demo.site.entity.User;
import com.financemanager.demo.site.repository.ItemRepository;
import com.financemanager.demo.site.repository.UserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class DefaultItemService implements ItemService {
	private final ItemRepository itemRepository;
	private final ItemConverter itemConverter;
	private final UserRepository userRepository;
	private final UserService userService;

	@Override
	public ItemDto saveItem(ItemDto itemDto) {
		itemDto.setUser(userService.getContextUser());
		Item savedItem = itemRepository.save(itemConverter.fromItemDtoToItem(itemDto));
		return itemConverter.fromItemToItemDto(savedItem);
	}

	@Override
	public void deleteItem(Integer itemId) {
		itemRepository.deleteById(itemId);
	}

	@Override
	public List<ItemDto> findAll() {
		return itemRepository.findAll().stream().map(itemConverter::fromItemToItemDto).collect(Collectors.toList());
	}

	@Override
	public List<ItemDto> findByCategoryId(int categoryId) {
		return itemRepository.findByCategoryIdAndUserId(categoryId, userService.getContextUser().getId()).stream()
				.map(itemConverter::fromItemToItemDto).collect(Collectors.toList());
	}
	
	@Override
	public List<ItemDto> findContextUserItems() {
		return itemRepository.findByUserId(userService.getContextUser().getId()).stream()
				.map(itemConverter::fromItemToItemDto).collect(Collectors.toList());
	}

}
