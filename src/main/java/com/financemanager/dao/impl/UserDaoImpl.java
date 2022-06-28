package com.financemanager.dao.impl;

import com.financemanager.dao.UserDao;
import com.financemanager.demo.site.entity.User;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@PropertySource(value = "classpath:/db/user/user_queries.properties")
public class UserDaoImpl implements UserDao {
    
    private final NamedParameterJdbcTemplate template;
    private final RowMapper<User> rowMapper;
    
    @Value("${find.by_email}")
    private String findByEmail;
    
    @Value("${find.all}")
    private String findAll;

    @Override
    public Optional<User> findByEmail(String email) {
        SqlParameterSource param = new MapSqlParameterSource("email", email);
        User user = null;
        try {
            user = template.queryForObject(findByEmail, param, rowMapper);
        } catch (DataAccessException ex) {
            ex.printStackTrace();
        }
        return Optional.ofNullable(user);
    }
    
    @Override
    public List<User> findAll() {
        return template.query(findAll, rowMapper);
    }

}
