package com.financemanager.demo.site.service;

import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;
import static java.time.temporal.TemporalAdjusters.lastDayOfYear;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.financemanager.demo.site.entity.Category;
import com.financemanager.demo.site.entity.projects.ProjectCategoryAndCost;
import com.financemanager.demo.site.exception.CategoryNotFoundException;
import com.financemanager.demo.site.repository.CategoryRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class DefaultCategoryService implements CategoryService{
	private final CategoryRepository categoryRepository;
	private final UserService userService;
	
@Override
public Category saveCategory(Category category) {
	return categoryRepository.save(category);
}

@Override
public void deleteCategory(Integer catId) {
	categoryRepository.deleteById(catId);
}

@Override
public List<Category> findAll() {
	return categoryRepository.findAll()
			.stream()
			.collect(Collectors.toList());
}

@Override
public Category findById(Integer id) throws CategoryNotFoundException {
	Category category = categoryRepository.findById(id)
			.orElseThrow(() -> new CategoryNotFoundException("Category not found"));
	return category;
}

@Override
public List<ProjectCategoryAndCost> getCategoriesAndCost(Optional<Integer> year,
		Optional<Integer> month) {
	if(year.isEmpty()) {return categoryRepository.getCategoriesAndCost(userService.getContextUser().getId());}
	LocalDate init = LocalDate.of(year.get(), (month.isPresent()) ? month.get() + 1 : 1, 1);
	Date dateStart = Date.from(init.atStartOfDay(ZoneId.systemDefault()).toInstant());
	Date dateEnd = Date.from(init.with((month.isPresent()) ? lastDayOfMonth() : lastDayOfYear()).atStartOfDay(ZoneId.systemDefault()).toInstant());
	return categoryRepository.getCategoriesAndCostByDate(userService.getContextUser().getId(), dateStart, dateEnd);
}


}
