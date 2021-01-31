package com.financemanager.demo.site.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.financemanager.demo.site.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {
	List<Item> findByCategoryIdAndUserId(int categoryId, int userId);
	//@Query("select td from TestChoosenDetails td join td.testDate ex where month(ex.examdate) = ?1") 
	//List<TestChoosenDetails> getByExamMonth(int month);
}
