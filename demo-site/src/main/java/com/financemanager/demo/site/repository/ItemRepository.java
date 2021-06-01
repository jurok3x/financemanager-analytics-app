package com.financemanager.demo.site.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.financemanager.demo.site.entity.Item;
import com.financemanager.demo.site.entity.projects.ProjectNameAndCount;

public interface ItemRepository extends JpaRepository<Item, Integer> {
	List<Item> findByUserIdAndCategoryIdAndDateGreaterThanEqualAndDateLessThanEqual(int userId, int categoryId,
			Date start, Date end);

	List<Item> findByUserId(int userId);

	List<Item> findByUserIdAndCategoryId(int userId, int categoryId);

	List<Item> findByUserIdAndDateGreaterThanEqualAndDateLessThanEqual(int userId, Date start, Date end);

	Integer countByUserIdAndCategoryIdAndDateGreaterThanEqualAndDateLessThanEqual(int userId, int categoryId,
			Date start, Date end);

	Integer countByUserIdAndCategoryId(int userId, int categoryId);

	@Query(value = "SELECT name, COUNT(name) as value, SUM (price) AS total\n"
			+ "FROM item_table WHERE user_id = :userId\n"
			+ "GROUP BY name ORDER BY value DESC LIMIT 5", nativeQuery = true)
	List<ProjectNameAndCount> getMostFrequentItems(@Param("userId") int userId);

	@Query(value = "SELECT name, COUNT(name) as value, SUM (price) AS total\n"
			+ "FROM item_table WHERE user_id = :userId AND category_id = :categoryId\n"
			+ "GROUP BY name ORDER BY value DESC LIMIT 5", nativeQuery = true)
	List<ProjectNameAndCount> getMostFrequentItemsByCategory(@Param("userId") int userId, @Param("categoryId") int categoryId);
}

/*
 * SELECT name, COUNT(name) as value_occurrence FROM item_table GROUP BY name
 * ORDER BY value_occurrence DESC LIMIT 5;
 */