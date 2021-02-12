package com.financemanager.demo.site.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.financemanager.demo.site.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

	Role findByName(String name);
}
