package com.financemanager.demo.site.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.financemanager.demo.site.dto.ItemDto;
import com.financemanager.demo.site.entity.Item;
import com.financemanager.demo.site.entity.projects.DatePartAndCost;
import com.financemanager.demo.site.entity.projects.ProjectNameAndCountAndCost;
import com.financemanager.demo.site.repository.ItemRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class DefaultItemService implements ItemService {
	private final ItemRepository itemRepository;
	private final ItemConverter itemConverter;
	private final UserService userService;
	private final UserConverter userConverter;

	@Override
	public ItemDto saveItem(ItemDto itemDto) {
		itemDto.setUserDto(userConverter.fromUserToUserDto(userService.getContextUser()));
		Item savedItem = itemRepository.save(itemConverter.fromItemDtoToItem(itemDto));
		return itemConverter.fromItemToItemDto(savedItem);
	}

	@Override
	public void deleteItem(Integer itemId) {
		itemRepository.deleteById(itemId);
	}

	@Override
	public List<ItemDto> findAll(Optional<String> year, Optional<String> month,
		Optional<Integer> limit, Optional<Integer> offset) {	
		String dateString = "%" + year.orElse("") + ((month.isPresent()) ? ("-" + month.get()) : "");
		return itemRepository
				.findByUserIdAndDate(userService.getContextUser().getId(), dateString,
						limit.orElse(10), offset.orElse(0))
				.stream().map(itemConverter::fromItemToItemDto).collect(Collectors.toList());		
	}

	@Override
	public List<ItemDto> findByCategory(int categoryId, Optional<String> year, Optional<String> month,
		Optional<Integer> limit, Optional<Integer> offset) {
		String dateString = "%" + year.orElse("") + ((month.isPresent()) ? ("-" + month.get()) : "");
		return itemRepository.findByUserIdAndCategoryIdAndDate(userService.getContextUser().getId(), categoryId,
				dateString, limit.orElse(10), offset.orElse(0))
				.stream().map(itemConverter::fromItemToItemDto).collect(Collectors.toList());
	}
	
	@Override
	public Integer countItemsByCategory(int cetegoryId, Optional<String> year, Optional<String> month) {
		String dateString = "%" + year.orElse("") + ((month.isPresent()) ? ("-" + month.get()) : "");
			return itemRepository.countByUserIdAndCategoryIdAndDate(userService.getContextUser().getId(),
					cetegoryId, dateString);	
	}
	
	@Override
	public List<ProjectNameAndCountAndCost> getMostFrequentItems(Optional<Integer> categoryId, Optional<String> year, Optional<String> month,
			Optional<Integer> limit, Optional<Integer> offset) {
		String dateString = "%" + year.orElse("") + ((month.isPresent()) ? ("-" + month.get()) : "");
		if(categoryId.isEmpty()) {
			return itemRepository.getMostFrequentItemsByDate(userService.getContextUser().getId(), dateString, limit.orElse(5), offset.orElse(0));
		}
		return  itemRepository.getMostFrequentItemsByCategoryAndDate(userService.getContextUser().getId(), categoryId.get(),
				dateString, limit.orElse(5), offset.orElse(0));
	}

	@Override
	public List<Integer> getAllYears() {
		return itemRepository.getAllYears(userService.getContextUser().getId());
	}

	@Override
	public List<DatePartAndCost> getMonthStatistics(Optional<Integer> categoryId, Optional<String> year) {
		String dateString = "%" + year.orElse("");
		if(categoryId.isEmpty()) {
			return itemRepository.getMonthStatistics(userService.getContextUser().getId(), dateString);
		}
		return itemRepository.getMonthStatisticsByCategory(userService.getContextUser().getId(), dateString, categoryId.get());
	}
	
}