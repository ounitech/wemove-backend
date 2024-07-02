package com.ounitech.wemove.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

    @Test
    void findById_not_found() throws Exception {
        // When
        Mockito.when(userService.findById(7)).thenReturn(Optional.empty());
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.get("/api/users/7")).andReturn().getResponse();

        // Then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

    @Test
    void deleteUser() throws Exception {
        // Given
        User user = new User();
        user.setFirstname("phil");
        user.setLastname("webb");

        Mockito.when(userService.findById(7)).thenReturn(Optional.of(user));

        //When
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.delete("/api/users/7")).andReturn().getResponse();

        // Then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void updateUser() throws Exception {
        User user = new User();
        user.setFirstname("phil");
        user.setLastname("webb");
        user.setEmail("webb@gmail.com");
        user.setJob("job");

        User user2 = new User();
        user2.setFirstname("aaaa");
        user2.setLastname("aaaaa");
        user2.setEmail("aaaaa");
        user2.setJob("aaa");

        userService.save(user2);

        Mockito.when(userService.findById(4)).thenReturn(Optional.of(user2));
        Mockito.when(userService.updateUser(4, user)).thenReturn(user);

        MockHttpServletResponse response = mockMvc.perform(
                MockMvcRequestBuilders.put("/api/users/4").contentType(MediaType.APPLICATION_JSON).content(asJsonString(user))).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString().contains("phil")).isTrue();
        assertThat(response.getContentAsString().contains("webb")).isTrue();
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
