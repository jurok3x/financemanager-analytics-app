package com.yurii.financeanalytics.dao;

import com.yurii.financeanalytics.entity.User;

import java.util.Optional;

public interface UserDao {
    
    Optional<User> findByEmail(String email);

}
