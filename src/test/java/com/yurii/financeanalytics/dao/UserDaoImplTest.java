package com.yurii.financeanalytics.dao;

import static org.junit.jupiter.api.Assertions.*;

import com.yurii.financeanalytics.configuration.TestDBConfiguration;
import com.yurii.financeanalytics.dao.impl.UserDaoImpl;
import com.yurii.financeanalytics.entity.Role;
import com.yurii.financeanalytics.entity.User;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest(classes = {TestDBConfiguration.class, UserDaoImpl.class})
class UserDaoImplTest {
    
    private static final String USER_EMAIL = "jurok3x@gmail.com";
    
    @Autowired
    private UserDao userDao;

    @Test
    void when_findByAll_then_returnCorrectList() {
        List<User> users = userDao.findAll();
        assertFalse(users.isEmpty());
        assertEquals(USER_EMAIL, userDao.findAll().get(0).getEmail());
    }
    
    @Test
    void when_findByEmail_then_returnCorrectUser() {
        assertEquals(Optional.of(getUser()), userDao.findByEmail(USER_EMAIL));
    }
    
    private static User getUser() {
        User user = new User();
        user.setId(1);
        user.setEmail(USER_EMAIL);
        user.setName("YuriiKotsiuba");
        user.setPassword("metropoliten");
        user.setRole(Role.ADMIN);
        return user;
    }

}
