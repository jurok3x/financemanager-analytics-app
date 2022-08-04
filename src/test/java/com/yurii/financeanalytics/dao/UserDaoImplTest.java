package com.yurii.financeanalytics.dao;

import static org.junit.jupiter.api.Assertions.*;

import com.yurii.financeanalytics.configuration.TestDBConfiguration;
import com.yurii.financeanalytics.dao.impl.UserDaoImpl;
import com.yurii.financeanalytics.entity.Role;
import com.yurii.financeanalytics.entity.User;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.stream.Collectors;

@SpringBootTest(classes = {TestDBConfiguration.class, UserDaoImpl.class})
class UserDaoImplTest {
    
    private static final String USER_EMAIL = "jurok3x@gmail.com";
    
    @Autowired
    private UserDao userDao;
    
    @Test
    void when_findByEmail_then_returnCorrectUser() {
        assertEquals(getUser().getEmail(), userDao.findByEmail(USER_EMAIL).get().getEmail());
        assertEquals(getUser().getRole().getName(), userDao.findByEmail(USER_EMAIL).get().getRole().getName());
        assertTrue(userDao.findByEmail(USER_EMAIL).get().getRole().getGrantedAuthorities()
                .stream().map(permission -> permission.getAuthority()).collect(Collectors.toSet()).contains("ROLE_ADMIN"));
    }
    
    private static User getUser() {
        User user = new User();
        user.setId(1);
        user.setEmail(USER_EMAIL);
        user.setName("Yurii Kotsiuba");
        user.setPassword("metropoliten");
        user.setRole(Role.builder()
                .id(1)
                .name("ROLE_ADMIN")
                .permissions(Arrays.asList("user:read", "user:write").stream().collect(Collectors.toSet()))
                .build());
        return user;
    }

}
