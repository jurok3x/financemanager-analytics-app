package com.financemanager.demo.site.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.financemanager.demo.site.entity.Group;

public interface GroupRepository extends JpaRepository<Group, Integer> {

}
