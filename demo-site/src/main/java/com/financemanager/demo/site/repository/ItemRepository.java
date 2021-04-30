package com.financemanager.demo.site.repository;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.financemanager.demo.site.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {
	List<Item> findByCategoryIdAndUserId(int categoryId, int userId);
	List<Item> findByUserId(int userId);
	List<Item> findByUserIdAndDateGreaterThanEqualAndDateLessThanEqual(int userId, Date start, Date end);
	Integer countByCategoryIdAndUserIdAndDateGreaterThanEqualAndDateLessThanEqual(int categoryId, int userId, Date start, Date end);
}
