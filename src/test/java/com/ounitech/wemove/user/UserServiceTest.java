package com.ounitech.wemove.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findById_nominal_case() {
        // Given
        User user = new User();
        user.setFirstname("jorgen");
        user.setLastname("hoeller");

        // When
        Mockito.when(userRepository.findById(11))
                .thenReturn(Optional.of(user));
        Optional<User> result = userService.findById(11);

        // Then
        assertThat(result.isPresent()).isTrue();
        assertThat(result.get().getFirstname()).isEqualTo("jorgen");
        assertThat(result.get().getLastname()).isEqualTo("hoeller");
    }

    @Test
    void findById_notFound() {
        //Given
        Mockito.when(userRepository.findById(11))
                .thenReturn(Optional.empty());

        // When
        Optional<User> result = userService.findById(11);

        // Then
        assertThat(result.isPresent()).isFalse();
    }

    @Test
    void findAllUsers() {
        // Given
        User user = new User();
        user.setFirstname("jorgen");
        user.setLastname("hoeller");

        User user2 = new User();
        user.setFirstname("cristiano");
        user.setLastname("ronaldo");

        //When
        Mockito.when(userRepository.findAll()).thenReturn(List.of(user, user2));
        List<User> result = userService.findAll();

        //Then
        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(2);
    }

}