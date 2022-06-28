package com.financemanager.configuration;

import com.financemanager.dao.extractor.ItemsAnalyticsViewMapper;
import com.financemanager.dao.extractor.MonthAnalyticsViewMapper;
import com.financemanager.dao.extractor.UserRowMapper;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@TestConfiguration
@Import(value = { UserRowMapper.class, ItemsAnalyticsViewMapper.class, MonthAnalyticsViewMapper.class })
public class TestDBConfiguration {
    
    @Bean
    public DataSource getDataSource() {
        DataSource dataSource = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:/db/user/schema.sql")
                .addScript("classpath:/db/user/test-data.sql")
                .addScript("classpath:/db/category/schema.sql")
                .addScript("classpath:/db/category/test-data.sql")
                .addScript("classpath:/db/item/schema.sql")
                .addScript("classpath:/db/item/test-data.sql")
                .addScript("classpath:/db/item/months-schema.sql")
                .addScript("classpath:/db/item/months-data.sql")
                .build();
        return dataSource;
    }
    
    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate() {
        return new NamedParameterJdbcTemplate(getDataSource());
    }

}
