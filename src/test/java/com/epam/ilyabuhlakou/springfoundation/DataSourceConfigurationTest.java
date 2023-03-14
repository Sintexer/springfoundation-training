package com.epam.ilyabuhlakou.springfoundation;

import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;

import javax.persistence.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@Import(DataSourceConfiguration.class)
class DataSourceConfigurationTest {

    @Autowired
    private EntityManager entityManager;

    @Getter
    @Setter
    @Entity
    @Table(name = "test_entities")
    private static class TestEntity {

        @Id
        @GeneratedValue
        private Long id;

        String name;
    }

    @Test
    void dataSourceSaveTest() {
        final String entityName = "entityName";
        TestEntity entity = new TestEntity();
        entity.setName(entityName);

        entityManager.persist(entity);
        entityManager.flush();
        entityManager.clear();

        assertNotNull(entity.id);

        long id = entity.id;

        TestEntity foundEntity = entityManager.find(TestEntity.class, id);

        assertNotNull(foundEntity);
        assertEquals(entityName, foundEntity.name);
    }
}
