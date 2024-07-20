package com.sprintplanner.planner.infra.datasource;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class DataSourceConfig {
    private Environment env;

    public DataSourceConfig(Environment env) {
        this.env = env;
    }

    @Bean
    DataSource getDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();

        dataSourceBuilder.driverClassName(env.getProperty("DATASOURCE_DRIVER_CLASS_NAME"));
        dataSourceBuilder.url(env.getProperty("DATASOURCE_URL"));
        dataSourceBuilder.username(env.getProperty("DATASOURCE_USER"));
        dataSourceBuilder.password(env.getProperty("DATASOURCE_PASSWORD"));

        return dataSourceBuilder.build();
    }
}
