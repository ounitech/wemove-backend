package com.ounitech.wemove.repositories;

import com.ounitech.wemove.models.Entry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;

import java.util.List;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.properties")
class EntryRepositoryTest {

    @Autowired
    private EntryRepository entryRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        jdbcTemplate.execute("""
                    INSERT INTO member(id, firstname, lastname, email, active)
                    VALUES (100, 'firstname', 'lastname', 'email', 1);
                """);

        jdbcTemplate.execute("""
                        INSERT INTO entry(id, memberid, entrytime, leavetime)
                        VALUES (1, 100, now(), now());
                """);

        jdbcTemplate.execute("""
                        INSERT INTO entry(id, memberid, entrytime, leavetime)
                        VALUES (2, 100, now(), now());
                """);
    }

    @Test
    void findByMemberId() {
        // Given // When
        List<Entry> entries = entryRepository.findByMemberId(100);

        // Then
        assertThat(entries)
                .hasSize(2)
                .extracting((Function<Entry, Integer>) Entry::getId)
                .containsExactlyInAnyOrder(1, 2);
    }
}