package com.financemanager.demo.site.repository;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.financemanager.demo.site.entity.Category;
import com.financemanager.demo.site.entity.projects.ProjectCategoryAndCost;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
	
	@Query(value = "SELECT  category_name,  SUM(price) AS total FROM item_table\n"
			+ "NATURAL JOIN categories WHERE date BETWEEN :dateStart AND :dateEnd AND user_id = :userId\n"
			+ "GROUP BY category_id, category_name ORDER BY category_id", nativeQuery = true)
	List<ProjectCategoryAndCost> getCategoriesAndCostByDate(
			@Param("userId") int userId,
			@Param("dateStart") Date dateStart,
			@Param("dateEnd") Date dateEnd);
	
	@Query(value = "SELECT  category_name,  SUM(price) AS total FROM item_table\n"
			+ "NATURAL JOIN categories WHERE user_id = :userId\n"
			+ "GROUP BY category_id, category_name ORDER BY category_id", nativeQuery = true)
	List<ProjectCategoryAndCost> getCategoriesAndCost(
			@Param("userId") int userId);
}
