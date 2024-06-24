package com.ounitech.wemove.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        User user1 = new User();
        user1.setFirstname("stephane");
        user1.setLastname("nicoll");

        User user2 = new User();
        user2.setFirstname("sebastien");
        user2.setLastname("deleuze");

        userRepository.save(user1);
        userRepository.save(user2);
    }

    @Test
    void findById() {
        // When
        Optional<User> user = userRepository.findById(1);

        // Then
        assertThat(user).isPresent();
        assertThat(user.get().getId()).isEqualTo(1);
        assertThat(user.get().getFirstname()).isEqualTo("stephane");
        assertThat(user.get().getLastname()).isEqualTo("nicoll");
    }

}