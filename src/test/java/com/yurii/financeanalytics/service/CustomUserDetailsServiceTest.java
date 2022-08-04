package com.yurii.financeanalytics.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import com.yurii.financeanalytics.dao.UserDao;
import com.yurii.financeanalytics.entity.Role;
import com.yurii.financeanalytics.entity.User;
import com.yurii.financeanalytics.service.impl.CustomUserDetailsService;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

@ExtendWith(value = { MockitoExtension.class })
class CustomUserDetailsServiceTest {

    @Mock
    UserDao userDao;
    
    @InjectMocks
    CustomUserDetailsService userService;
    
    @Test
    void whenEmailProvided_returnCorrectUser() {
        Optional<User> expectedUser = prepareUser();
        given(userDao.findByEmail(Mockito.anyString())).willReturn(expectedUser);
        assertEquals(expectedUser.get().getEmail(), userService.loadUserByUsername("jurok3x@gmail.com").getUsername());
        verify(userDao).findByEmail("jurok3x@gmail.com");
    }
    
    @AfterEach
    void tearDown() {
        verifyNoMoreInteractions(userDao);
    }
    
    private static Optional<User> prepareUser() {
        User user = new User();
        user.setId(1);
        user.setEmail("jurok3x@gmail.com");
        user.setName("Yurii Kotsiuba");
        user.setPassword("pazzword");
        user.setRole(Role.builder()
                .id(1)
                .name("ROLE_ADMIN")
                .permissions(Arrays.asList("user:read", "user:write").stream().collect(Collectors.toSet()))
                .build());
        return Optional.of(user);
    }
}
