package com.financemanager.demo.site.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.financemanager.demo.site.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {
	List<Item> findByCategoryIdAndUserId(int categoryId, int userId);
	List<Item> findByUserId(int userId);
	
	@Query("SELECT * FROM public.item_table WHERE EXTRACT (MONTH FROM date)=?1 AND EXTRACT (YEAR FROM date)=?2") 
	List<Item> getByMonthAndYear(int month, int year);
	
	//@Query("select td from TestChoosenDetails td join td.testDate ex where month(ex.examdate) = ?1") 
	//List<TestChoosenDetails> getByExamMonth(int month);
}
