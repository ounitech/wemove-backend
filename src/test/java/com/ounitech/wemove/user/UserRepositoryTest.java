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
        user1.setEmail("stephanenicoll@gmail.com");

        User user2 = new User();
        user2.setFirstname("sebastien");
        user2.setLastname("deleuze");
        user2.setEmail("sebastiendeleuze@gmail.com");


        userRepository.save(user1);
        userRepository.save(user2);
    }

    @Test
    void findById() {
        // When
        Optional<User> user = userRepository.findById(4);

        // Then
        assertThat(user).isPresent();
        assertThat(user.get().getId()).isEqualTo(4);
        assertThat(user.get().getFirstname()).isEqualTo("stephane");
        assertThat(user.get().getLastname()).isEqualTo("nicoll");
    }

}