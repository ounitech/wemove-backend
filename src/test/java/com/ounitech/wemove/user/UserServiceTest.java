package com.ounitech.wemove.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

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

        Mockito.when(userRepository.findById(11))
                .thenReturn(Optional.of(user));

        // When
        Optional<User> result = userService.findById(11);

        // Then
        assertThat(result.isPresent()).isTrue();
        assertThat(result.get().getFirstname()).isEqualTo("jorgen");
        assertThat(result.get().getLastname()).isEqualTo("hoeller");
    }

    @Test
    void findById_notFound() {
        // TODO : handle not found id case
    }

}