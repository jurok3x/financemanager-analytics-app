package com.financemanager.demo.site.repository;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.financemanager.demo.site.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {
	List<Item> findByCategoryIdAndUserId(int categoryId, int userId);
	List<Item> findByUserId(int userId);
	List<Item> findByUserIdAndDateGreaterThanEqualAndDateLessThanEqual(int userId, Date start, Date end);
	
	
	//@Query("select td from TestChoosenDetails td join td.testDate ex where month(ex.examdate) = ?1") 
	//List<TestChoosenDetails> getByExamMonth(int month);
	//@Query(value = "SELECT * FROM public.item_table WHERE user=?1 AND EXTRACT (MONTH FROM date)=?2 AND EXTRACT (YEAR FROM date)=?3", nativeQuery = true)
	//@Query("select item from item_table item join item.date date where month(date.itemdte) = ?1") 
	//List<Item> getByUserIdAndMonthAndYear(int userId, int month, int year);
}
