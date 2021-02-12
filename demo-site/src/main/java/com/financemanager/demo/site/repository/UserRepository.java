package com.financemanager.demo.site.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.financemanager.demo.site.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByLogin(String login);
	
	List<User> findByRoleId(Integer id);
}
