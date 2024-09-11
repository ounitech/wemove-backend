package com.ounitech.wemove.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ounitech.wemove.models.Gender;
import com.ounitech.wemove.models.Role;
import com.ounitech.wemove.models.Staff;
import com.ounitech.wemove.services.StaffService;
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

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(StaffController.class)
class StaffControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

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

    @Test
    void saveStaffTest() throws Exception {

        Staff staff = new Staff();
        staff.setId(1000);
        staff.setFirstname("firstname");
        staff.setLastname("lastname");
        staff.setEmail("email@gmail.com");
        staff.setPicture("picture");
        staff.setPhone("12121212");
        staff.setActive(true);
        staff.setRole(new Role());
        staff.setAddress("address");
        staff.setGender(Gender.Male);

        when(staffService.findByEmail(any(String.class))).thenReturn(null);
        when(staffService.save(any(Staff.class))).thenReturn(staff);

        MockHttpServletResponse response = mockMvc.perform(
                post("/api/staff/save").contentType(MediaType.APPLICATION_JSON).content(asJsonString(staff))).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.getContentAsString().contains("firstname")).isTrue();
        assertThat(response.getContentAsString().contains("lastname")).isTrue();
        assertThat(response.getContentAsString().contains("email@gmail.com")).isTrue();
    }

    @Test
    void saveStaffTest_DuplicateEmail() throws Exception {
        Staff staff = new Staff();
        staff.setId(1000);
        staff.setFirstname("firstname");
        staff.setLastname("lastname");
        staff.setEmail("email@gmail.com");
        staff.setPicture("picture");
        staff.setPhone("12121212");
        staff.setActive(true);
        staff.setRole(new Role());
        staff.setAddress("address");
        staff.setGender(Gender.Male);

        Staff staff1 = new Staff();
        staff1.setEmail("email@gmail.com");

        when(staffService.findByEmail(any(String.class))).thenReturn(staff1);

        MockHttpServletResponse response = mockMvc.perform(
                MockMvcRequestBuilders.post("/api/staff/save").contentType(MediaType.APPLICATION_JSON).content(asJsonString(staff))).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CONFLICT.value());
    }

    @Test
    void saveMemberTest_InvalidPhoneNumber() throws Exception {
        Staff staff = new Staff();
        staff.setId(1000);
        staff.setFirstname("firstname");
        staff.setLastname("lastname");
        staff.setEmail("email@gmail.com");
        staff.setPicture("picture");
        staff.setPhone("phone");
        staff.setActive(true);
        staff.setRole(new Role());
        staff.setAddress("address");
        staff.setGender(Gender.Male);

        Mockito.when(staffService.findByEmail(any(String.class))).thenReturn(null);

        MockHttpServletResponse response = mockMvc.perform(
                MockMvcRequestBuilders.post("/api/staff/save").contentType(MediaType.APPLICATION_JSON).content(asJsonString(staff))).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }


    @Test
    void saveStaffTest_missing_fields() throws Exception {
        //Given
        Staff staff = new Staff();
        staff.setId(1000);
        staff.setFirstname("firstname");
        staff.setAddress("address");
        staff.setGender(Gender.Male);

        when(staffService.findByEmail(any(String.class))).thenReturn(null);

        // When
        MockHttpServletResponse response = mockMvc.perform(
                MockMvcRequestBuilders.post("/api/staff/save").contentType(MediaType.APPLICATION_JSON).content(asJsonString(staff))).andReturn().getResponse();

        // Then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void findById_nominal_case() throws Exception {
        // Given
        Staff staff = new Staff();
        staff.setId(7);
        staff.setFirstname("firstname");
        staff.setLastname("lastname");
        staff.setEmail("email@gmail.com");
        staff.setPicture("picture");
        staff.setPhone("12121212");
        staff.setActive(true);
        staff.setRole(new Role());
        staff.setAddress("address");
        staff.setGender(Gender.Male);

        when(staffService.findById(7)).thenReturn(Optional.of(staff));

        // When
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.get("/api/staff/findById/7")).andReturn().getResponse();

        // Then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString().contains("firstname")).isTrue();
        assertThat(response.getContentAsString().contains("lastname")).isTrue();
    }

    @Test
    void findById_not_found() throws Exception {
        // When
        when(staffService.findById(7)).thenReturn(Optional.empty());

        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.get("/api/staff/findById/7")).andReturn().getResponse();

        // Then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

    @Test
    void deleteStaff() throws Exception {
        // Given
        Staff staff = new Staff();
        staff.setId(1000);
        staff.setFirstname("firstname");
        staff.setLastname("lastname");
        staff.setEmail("email@gmail.com");
        staff.setPicture("picture");
        staff.setPhone("12121212");
        staff.setActive(true);
        staff.setRole(new Role());
        staff.setAddress("address");
        staff.setGender(Gender.Male);

        when(staffService.findById(7)).thenReturn(Optional.of(staff));

        //When
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.delete("/api/staff/7")).andReturn().getResponse();

        // Then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void updateStaff() throws Exception {
        Staff staff = new Staff();
        staff.setId(1000);
        staff.setFirstname("firstname");
        staff.setLastname("lastname");
        staff.setEmail("email@gmail.com");
        staff.setPicture("picture");
        staff.setPhone("12121212");
        staff.setActive(true);
        staff.setRole(new Role());
        staff.setAddress("address");
        staff.setGender(Gender.Male);

        Staff staff2 = new Staff();
        staff.setId(1000);
        staff.setFirstname("aaa");
        staff.setLastname("aaa");
        staff.setEmail("aa@gmail.com");
        staff.setPicture("aaa");
        staff.setPhone("12121212");
        staff.setActive(true);
        staff.setRole(new Role());
        staff.setAddress("aaa");
        staff.setGender(Gender.Male);


        when(staffService.findById(any(Integer.class))).thenReturn(Optional.of(staff2));
        when(staffService.updateStaff(any(Integer.class), any(Staff.class))).thenReturn(staff);

        MockHttpServletResponse response = mockMvc.perform(
                MockMvcRequestBuilders.put("/api/staff/update/4").contentType(MediaType.APPLICATION_JSON).content(asJsonString(staff))).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString().contains("firstname")).isTrue();
        assertThat(response.getContentAsString().contains("lastname")).isTrue();
    }

    @Test
    void activateStaffTest() throws Exception {
        Staff staff = new Staff();
        staff.setId(1000);
        staff.setFirstname("firstname");
        staff.setLastname("lastname");
        staff.setEmail("email@gmail.com");
        staff.setPicture("picture");
        staff.setPhone("12121212");
        staff.setActive(false);
        staff.setRole(new Role());
        staff.setAddress("address");
        staff.setGender(Gender.Male);

        Staff activatedStaff = new Staff();
        activatedStaff.setId(1000);
        activatedStaff.setFirstname("firstname");
        activatedStaff.setLastname("lastname");
        activatedStaff.setEmail("email@gmail.com");
        activatedStaff.setPicture("picture");
        activatedStaff.setPhone("12121212");
        activatedStaff.setActive(true);
        activatedStaff.setRole(new Role());
        activatedStaff.setAddress("address");
        activatedStaff.setGender(Gender.Female);

        when(staffService.findById(any(Integer.class))).thenReturn(Optional.of(staff));
        when(staffService.activateStaff(1000)).thenReturn(activatedStaff);

        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.put("/api/staff/activate/1000"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    private String asJsonString(final Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}