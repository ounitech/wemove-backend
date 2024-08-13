package com.ounitech.wemove.controllers;

import com.ounitech.wemove.models.Staff;
import com.ounitech.wemove.services.StaffService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(StaffController.class)
class StaffControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StaffService staffService;

    @Test
    void findAllStaff() throws Exception {
        // Given
        when(staffService.findAll())
                .thenReturn(List.of(new Staff(), new Staff()));

        // When
        MockHttpServletResponse response = mockMvc.perform(
                get("/api/staff")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        // Then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }
}