package com.epam.ilyabuhlakou.springfoundation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@Sql("/generate_test_schema.sql")
@SpringBootTest
@EnableConfigurationProperties(DataSourceConfiguration.class)
@TestPropertySource(
        properties = {
                "springfoundation.db.url=jdbc:h2:mem:springfoundation-test;DB_CLOSE_ON_EXIT=FALSE",
                "springfoundation.db.username=h2",
                "springfoundation.db.password=h2",
//               if parameter is missing, bean shouldn't be instantiated
//               "springfoundation.db.driverClassName=org.h2.Driver"
        }
)
class DataSourceConfigurationFailureTest {

    @Autowired
    private DataSource dataSource;

    @Test
    void dataSourceSaveTest() throws SQLException {
        // Verify it's not datasource from DataSourceConfiguration
        assertFalse(dataSource.getConnection().getMetaData().getURL()
                .contains("jdbc:h2:mem:springfoundation-test;DB_CLOSE_ON_EXIT=FALSE"));
    }
}
