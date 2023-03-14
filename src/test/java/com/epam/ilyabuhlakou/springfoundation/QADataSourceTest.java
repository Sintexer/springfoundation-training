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

@ActiveProfiles("qa")
@Import(DataSourceConfiguration.class)
@EnableConfigurationProperties(DataSourceConfiguration.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class QADataSourceTest {

    @PersistenceContext
    private EntityManager em;

    @Getter
    @Setter
    @Entity
    @Table(name = "qa_entities")
    private static class QAEntity {
        @Id
        private int id;

        private String description;
    }

    @Test
    void testQADataSource() {
        QAEntity entity = new QAEntity();
        entity.setId(1);
        entity.setDescription("false");

        em.persist(entity);
        int id = entity.getId();

        em.flush();
        em.clear();

        QAEntity qaEntity = em.find(QAEntity.class, id);
        Assertions.assertNotNull(qaEntity);
        Assertions.assertEquals(entity.getDescription(), qaEntity.getDescription());
        Assertions.assertEquals(entity.getId(), qaEntity.getId());
    }
}
