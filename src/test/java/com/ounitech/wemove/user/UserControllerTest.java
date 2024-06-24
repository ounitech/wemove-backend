package com.ounitech.wemove.user;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void findById_nominal_case() throws Exception {
        // Given
        User user = new User();
        user.setFirstname("phil");
        user.setLastname("webb");

        Mockito.when(userService.findById(7)).thenReturn(Optional.of(user));

        // When
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.get("/api/users/7")).andReturn().getResponse();

        // Then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString().contains("phil")).isTrue();
        assertThat(response.getContentAsString().contains("webb")).isTrue();
    }

    void findById_not_found() throws Exception {
        // TODO : handle not found id case
    }
}