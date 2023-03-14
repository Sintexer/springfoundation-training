package com.epam.ilyabuhlakou.springfoundation;

import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import javax.persistence.*;

@ActiveProfiles("dev")
@Import(DataSourceConfiguration.class)
@EnableConfigurationProperties(DataSourceConfiguration.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class DevDataSourceTest {

    @PersistenceContext
    private EntityManager em;


    @Getter
    @Setter
    @Entity
    @Table(name = "dev_entities")
    private static class DevEntity {
        @Id
        private int id;

        private String name;
    }

    @Test
    void testQADataSource() {
        DevEntity entity = new DevEntity();
        entity.setId(1);
        entity.setName("name");

        em.persist(entity);
        int id = entity.getId();

        em.flush();
        em.clear();

        DevEntity devEntity = em.find(DevEntity.class, id);
        Assertions.assertNotNull(devEntity);
        Assertions.assertEquals(devEntity.getName(), devEntity.getName());
        Assertions.assertEquals(entity.getId(), devEntity.getId());
    }
}
