package com.financemanager.demo.site.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.financemanager.demo.site.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
