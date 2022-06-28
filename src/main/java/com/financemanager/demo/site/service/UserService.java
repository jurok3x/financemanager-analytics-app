package com.financemanager.demo.site.service;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.financemanager.demo.site.entity.User;

@Component
public interface UserService {
    Optional<User> findByEmail(String login);
}
