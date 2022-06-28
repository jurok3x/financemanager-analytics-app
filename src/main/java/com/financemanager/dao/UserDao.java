package com.financemanager.dao;

import com.financemanager.demo.site.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    
    Optional<User> findByEmail(String email);
    
    List<User> findAll();

}
