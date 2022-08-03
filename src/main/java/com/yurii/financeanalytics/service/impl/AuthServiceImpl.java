package com.yurii.financeanalytics.service.impl;

import com.yurii.financeanalytics.config.jwt.JwtProvider;
import com.yurii.financeanalytics.dao.UserDao;
import com.yurii.financeanalytics.entity.User;
import com.yurii.financeanalytics.entity.payload.AuthRequest;
import com.yurii.financeanalytics.entity.payload.AuthResponse;
import com.yurii.financeanalytics.service.AuthService;

import lombok.AllArgsConstructor;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    
    private static final String TOKEN_TYPE = "Bearer";
    private static final String WRONG_PASSWORD_ERROR = "Wrong password for user with email %s";
    private static final String USER_EMAIL_NOT_FOUND_ERROR = "User with email - %s not found";
    private JwtProvider jwtProvider;
    private PasswordEncoder passwordEncoder;
    private UserDao userDao;
    
    @Override
    public AuthResponse login(AuthRequest request) {
        User user = userDao.findByEmail(request.getEmail()).orElseThrow(() -> new BadCredentialsException(
                String.format(USER_EMAIL_NOT_FOUND_ERROR, request.getEmail())));
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadCredentialsException(
                    String.format(WRONG_PASSWORD_ERROR, request.getEmail()));
        }
        return new AuthResponse(jwtProvider.generateToken(user.getEmail()), TOKEN_TYPE);
    }

}
