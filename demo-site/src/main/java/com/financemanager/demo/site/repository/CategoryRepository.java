package com.financemanager.demo.site.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.financemanager.demo.site.entity.Category;
import com.financemanager.demo.site.entity.projects.ProjectCategoryAndCost;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
	
	@Query(value = "SELECT  category_name,  SUM(price) AS total FROM item_table\n"
			+ "NATURAL JOIN categories WHERE CAST (date as text) LIKE :dateString\n"
			+ "AND user_id = :userId GROUP BY category_id, category_name ORDER BY category_id",
			nativeQuery = true)
	List<ProjectCategoryAndCost> getCategoriesAndCostByDate(
			@Param("userId") int userId,
			@Param("dateSting") String dateStart);
}
