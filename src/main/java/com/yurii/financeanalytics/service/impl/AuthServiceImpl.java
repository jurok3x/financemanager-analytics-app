package com.yurii.financeanalytics.service.impl;

import com.yurii.financeanalytics.config.jwt.JwtProvider;
import com.yurii.financeanalytics.dao.UserDao;
import com.yurii.financeanalytics.entity.User;
import com.yurii.financeanalytics.entity.payload.AuthRequest;
import com.yurii.financeanalytics.entity.payload.AuthResponse;
import com.yurii.financeanalytics.service.AuthService;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@PropertySource(value = {"classpath:/messages/errors.properties"})
public class AuthServiceImpl implements AuthService {
    
    private static final String TOKEN_TYPE = "Bearer";
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;
    private final UserDao userDao;
    
    @Value("wrong_password.error")
    private String wrongPasswordError;
    @Value("email_not_found.error")
    private String userEmailNotFoundError;
    
    @Override
    public AuthResponse login(AuthRequest request) {
        User user = userDao.findByEmail(request.getEmail()).orElseThrow(() -> new BadCredentialsException(
                String.format(userEmailNotFoundError, request.getEmail())));
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadCredentialsException(
                    String.format(wrongPasswordError, request.getEmail()));
        }
        return new AuthResponse(jwtProvider.generateToken(user.getEmail()), TOKEN_TYPE);
    }

}
