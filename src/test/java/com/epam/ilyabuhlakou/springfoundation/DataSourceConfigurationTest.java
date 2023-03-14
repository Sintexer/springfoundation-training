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

@Sql("/generate_test_schema.sql")
@SpringBootTest
@EnableConfigurationProperties(DataSourceConfiguration.class)
@TestPropertySource(
        properties = {
                "springfoundation.db.url=jdbc:h2:mem:springfoundation-test;DB_CLOSE_ON_EXIT=FALSE",
                "springfoundation.db.username=h2",
                "springfoundation.db.password=h2",
                "springfoundation.db.driverClassName=org.h2.Driver"
        }
)
class DataSourceConfigurationTest {

    @Autowired
    private DataSource dataSource;

    @Test
    void dataSourceSaveTest() throws SQLException {
        String query = "insert into \"test_entities\" (\"id\", \"name\") VALUES(1, 'test1')";
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            con = dataSource.getConnection();
            stmt = con.createStatement();
            int id = stmt.executeUpdate(query);
            stmt.close();

            stmt = con.createStatement();
            rs = stmt.executeQuery("select \"id\", \"name\" from \"test_entities\"");
            rs.next();
            assertEquals(1, rs.getInt("id"));
            assertEquals("test1", rs.getString("name"));
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
