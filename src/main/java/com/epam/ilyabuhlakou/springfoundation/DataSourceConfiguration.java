package com.epam.ilyabuhlakou.springfoundation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfiguration {

    @Bean
    public DataSource dataSource(EmbeddedDatabase embeddedDatabase) {
        return embeddedDatabase;
    }
}
