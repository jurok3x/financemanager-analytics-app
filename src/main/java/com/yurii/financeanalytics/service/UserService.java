package com.yurii.financeanalytics.service;

import java.util.Optional;

import com.yurii.financeanalytics.entity.User;

import org.springframework.stereotype.Component;

@Component
public interface UserService {
    Optional<User> findByEmail(String login);
}
