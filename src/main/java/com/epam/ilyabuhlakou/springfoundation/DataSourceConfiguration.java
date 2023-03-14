package com.epam.ilyabuhlakou.springfoundation;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Getter
@Setter
@Configuration
@ConditionalOnProperty(prefix = "springfoundation.db", name = {"url", "username", "password", "driverClassName"})
@ConfigurationProperties(prefix = "springfoundation.db")
public class DataSourceConfiguration {

    private String url;

    private String username;

    private String password;

    private String driverClassName;

    @Bean
    @ConditionalOnMissingBean
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .url(url)
                .username(username)
                .password(password)
                .driverClassName(driverClassName)
                .build();
    }
}
