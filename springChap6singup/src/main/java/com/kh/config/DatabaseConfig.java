package com.kh.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

//데이터 베이스에 테이블이 없을 경우 테이블 생성
@Configuration

public class DatabaseConfig {

    @Bean
    public DataSourceInitializer dataSourceInitializer(DataSource dataSource) {
        DataSourceInitializer initializer = new DataSourceInitializer();
        initializer.setDataSource(dataSource);
        initializer.setDatabasePopulator(databasePopulator(dataSource));
        return initializer;
    }

    private ResourceDatabasePopulator databasePopulator(DataSource dataSource) {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        
        if (!tableExists(dataSource, "Users")) {
            populator.addScript(new ClassPathResource("sql/create-users-table.sql"));
        }
        return populator;
    }

    private boolean tableExists(DataSource dataSource, String tableName) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String query = "SELECT COUNT(*) FROM ALL_TABLES WHERE TABLE_NAME = UPPER(?)";
        int count = jdbcTemplate.queryForObject(query, Integer.class, tableName.toUpperCase());
        return count > 0;
    }
}