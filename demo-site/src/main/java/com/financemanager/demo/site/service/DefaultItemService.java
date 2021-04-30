package com.financemanager.demo.site.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Service;

import com.financemanager.demo.site.dto.ItemDto;
import com.financemanager.demo.site.entity.Category;
import com.financemanager.demo.site.entity.Item;
import com.financemanager.demo.site.exception.NoSuchCategoryException;
import com.financemanager.demo.site.repository.ItemRepository;

import lombok.AllArgsConstructor;

import static java.time.temporal.TemporalAdjusters.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@AllArgsConstructor
@Service
public class DefaultItemService implements ItemService {
	private final ItemRepository itemRepository;
	private final ItemConverter itemConverter;
	private final UserService userService;
	private final UserConverter userConverter;
	private final CategoryService categoryService;

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
	public List<ItemDto> findAll() {
		return itemRepository.findByUserId(userService.getContextUser().getId()).stream()
				.map(itemConverter::fromItemToItemDto).collect(Collectors.toList());
	}

	@Override
	public List<ItemDto> findByCategoryId(int categoryId) {
		return itemRepository.findByCategoryIdAndUserId(categoryId, userService.getContextUser().getId()).stream()
				.map(itemConverter::fromItemToItemDto).collect(Collectors.toList());
	}

	@Override
	public List<ItemDto> getCurrentUserItems() {
		Date start = Date.from(LocalDate.now(ZoneId.systemDefault()).withDayOfMonth(1)
				.atStartOfDay(ZoneId.systemDefault()).toInstant());
		Date end = Date.from(LocalDate.now(ZoneId.systemDefault()).with(lastDayOfMonth())
				.atStartOfDay(ZoneId.systemDefault()).toInstant());
		return itemRepository
				.findByUserIdAndDateGreaterThanEqualAndDateLessThanEqual(userService.getContextUser().getId(), start,
						end)
				.stream().map(itemConverter::fromItemToItemDto).collect(Collectors.toList());
	}

	@Override
	public List<ItemDto> getSpecifiedUserItems(int year, int month, Optional<String> sort, Optional<Boolean> isReversed) {
		LocalDate init = LocalDate.of(year, month, 1);
		Comparator<ItemDto> comparator;
		if (sort.isPresent()) {
			switch (sort.get()) {
			case "name":
				comparator = (isReversed.isPresent() && isReversed.get().equals(true)) ?
				Comparator.comparing(ItemDto::getName).reversed(): Comparator.comparing(ItemDto::getName);
				break;
			case "price":
				comparator = (isReversed.isPresent() && isReversed.get().equals(true)) ?
				Comparator.comparing(ItemDto::getPrice).reversed(): Comparator.comparing(ItemDto::getPrice);
				break;
			case "category":
				comparator = (isReversed.isPresent() && isReversed.get().equals(true)) ?
				Comparator.comparing(ItemDto::getCategory).reversed(): Comparator.comparing(ItemDto::getCategory);
				break;
			case "date":
				comparator = (isReversed.isPresent() && isReversed.get().equals(true)) ?
				Comparator.comparing(ItemDto::getDate).reversed(): Comparator.comparing(ItemDto::getDate);
				break;
			default:
					comparator = (isReversed.isPresent() && isReversed.get().equals(true)) ?
					Comparator.comparing(ItemDto::getId).reversed(): Comparator.comparing(ItemDto::getId);
				break;
			}
		} else {
			comparator = Comparator.comparing(ItemDto::getId);
		}
		Date start = Date.from(init.atStartOfDay(ZoneId.systemDefault()).toInstant());
		Date end = Date.from(init.with(lastDayOfMonth()).atStartOfDay(ZoneId.systemDefault()).toInstant());
		return itemRepository
				.findByUserIdAndDateGreaterThanEqualAndDateLessThanEqual(userService.getContextUser().getId(), start,end)
				.stream().map(itemConverter::fromItemToItemDto).sorted(comparator).collect(Collectors.toList());
	}

	@Override
	public List<ItemDto> saveItemsFromExelFile(String path) {
		List<Item> items = new ArrayList<Item>();
		try {
			FileInputStream fis = new FileInputStream(new File(path));
			HSSFWorkbook wb = new HSSFWorkbook(fis);
			HSSFSheet sheet = wb.getSheetAt(0);
			Item item = new Item();
			Date date = new Date();
			for (Row row : sheet) {
				for (Cell cell : row) {
					switch (cell.getCellTypeEnum()) {
					case STRING:
						item = new Item();
						item.setUser(userService.getContextUser());
						item.setName(cell.getStringCellValue().substring(0, 1).toUpperCase()
								+ cell.getStringCellValue().substring(1).toLowerCase());
						item.setDate(date);
						Category category = new Category();
						switch (cell.getCellStyle().getFillForegroundColor()) {
						case 45:
							category = categoryService.findById(1);
							break;
						case 13:
							category = categoryService.findById(2);
							break;
						case 15:
							category = categoryService.findById(3);
							break;
						case 11:
							category = categoryService.findById(4);
							break;
						case 46:
							category = categoryService.findById(5);
							break;
						case 10:
							category = categoryService.findById(6);
							break;
						case 53:
							category = categoryService.findById(7);
							break;
						case 61:
							category = categoryService.findById(8);
							break;
						case 60:
							category = categoryService.findById(9);
							break;
						default:
							category = categoryService.findById(10);
							break;
						}
						item.setCategory(category);
						break;
					case NUMERIC:
						if (DateUtil.isCellDateFormatted(cell)) {
							date = cell.getDateCellValue();
						} else {
							item.setPrice(cell.getNumericCellValue());
							items.add(item);							
						}
						break;
					case FORMULA:
						break;
					default:
						break;
					}
				}
			}
			wb.close();
			items.forEach((storedItem) -> itemRepository.save(storedItem));
			return items.stream().map(itemConverter::fromItemToItemDto).collect(Collectors.toList());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (NoSuchCategoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public Integer countItemsByCategory(int cetegoryId, int year, int month) {
		LocalDate init = LocalDate.of(year, month, 1);
		Date start = Date.from(init.atStartOfDay(ZoneId.systemDefault()).toInstant());
		Date end = Date.from(init.with(lastDayOfMonth()).atStartOfDay(ZoneId.systemDefault()).toInstant());
		return itemRepository.countByCategoryIdAndUserIdAndDateGreaterThanEqualAndDateLessThanEqual(cetegoryId,
				userService.getContextUser().getId(), start, end);
	}

}
