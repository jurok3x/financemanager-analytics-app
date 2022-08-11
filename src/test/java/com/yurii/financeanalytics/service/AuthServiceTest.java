package com.yurii.financeanalytics.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import com.yurii.financeanalytics.config.jwt.JwtProvider;
import com.yurii.financeanalytics.dao.UserDao;
import com.yurii.financeanalytics.entity.Role;
import com.yurii.financeanalytics.entity.User;
import com.yurii.financeanalytics.entity.payload.AuthRequest;
import com.yurii.financeanalytics.service.impl.AuthServiceImpl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

@ExtendWith(value = { MockitoExtension.class })
public class AuthServiceTest {
    
    private static final String USER_PASSWORD = "metropoliten";
    @Mock
    UserDao userDao;
    @Mock
    private JwtProvider jwtProvider;
    private PasswordEncoder passwordEncoder;
    private AuthService authService;
    
    @BeforeEach
    void setUp() {
        passwordEncoder = new BCryptPasswordEncoder();
        authService = new AuthServiceImpl(jwtProvider, passwordEncoder, userDao);
        ReflectionTestUtils.setField(authService, "wrongPasswordError", "Wrong password for user with email {}");
        ReflectionTestUtils.setField(authService, "userEmailNotFoundError", "User with email {} not found");
    }
    
    @Test
    void whenLogin_thenReturnToken() {
        User user = getUser();
        AuthRequest request = new AuthRequest(user.getEmail(), USER_PASSWORD);
        given(userDao.findByEmail(Mockito.anyString())).willReturn(Optional.of(user));
        given(jwtProvider.generateToken(Mockito.anyString(), Mockito.anyInt())).willReturn("token");
        assertEquals("token", authService.login(request).getToken());
        verify(userDao).findByEmail(Mockito.anyString());
        verify(jwtProvider).generateToken(Mockito.anyString(), Mockito.anyInt());
    }
    
    @Test
    void whenEmailNotFound_thenThrowException() {
        given(userDao.findByEmail(Mockito.anyString())).willReturn(Optional.empty());
        AuthRequest request = new AuthRequest("empty_email", USER_PASSWORD);
        assertThrows(BadCredentialsException.class, () -> authService.login(request));
        verify(userDao).findByEmail(Mockito.anyString());
        verifyNoInteractions(jwtProvider);
    }
    
    @Test
    void whenPasswordWrong_thenThrowException() {
        User user = getUser();
        AuthRequest request = new AuthRequest(user.getEmail(), "wrong_password");
        given(userDao.findByEmail(Mockito.anyString())).willReturn(Optional.of(user));
        assertThrows(BadCredentialsException.class, () -> authService.login(request));
        verify(userDao).findByEmail(Mockito.anyString());
        verifyNoInteractions(jwtProvider);
    }
    
    @AfterEach
    void tearDown() {
        verifyNoMoreInteractions(userDao);
    }
    
    private User getUser() {
        User user = new User();
        user.setId(1);
        user.setEmail("jurok3x@gmail.com");
        user.setName("Yurii Kotsiuba");
        user.setPassword(passwordEncoder.encode(USER_PASSWORD));
        user.setRole(Role.builder()
                .id(1)
                .name("ROLE_ADMIN")
                .permissions(Arrays.asList("user:read", "user:write").stream().collect(Collectors.toSet()))
                .build());
        return user;
    }
  
}
