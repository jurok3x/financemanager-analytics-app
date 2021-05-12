package com.financemanager.demo.site.repository;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.financemanager.demo.site.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {
	List<Item> findByUserIdAndCategoryIdAndDateGreaterThanEqualAndDateLessThanEqual(int userId, int categoryId, Date start, Date end);
	List<Item> findByUserId(int userId);
	List<Item> findByUserIdAndCategoryId(int userId, int categoryId);
	List<Item> findByUserIdAndDateGreaterThanEqualAndDateLessThanEqual(int userId, Date start, Date end);	
	Integer countByUserIdAndCategoryIdAndDateGreaterThanEqualAndDateLessThanEqual(int userId, int categoryId, Date start, Date end);
	Integer countByUserIdAndCategoryId(int userId, int categoryId);
}
