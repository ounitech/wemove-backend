package com.ounitech.wemove.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ounitech.wemove.controllers.MemberController;
import com.ounitech.wemove.models.Member;
import com.ounitech.wemove.services.MemberService;
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

@WebMvcTest(MemberController.class)
class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MemberService memberService;

    @Test
    void findById_nominal_case() throws Exception {
        // Given
        Member member = new Member();
        member.setFirstname("phil");
        member.setLastname("webb");

        Mockito.when(memberService.findById(7)).thenReturn(Optional.of(member));

        // When
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.get("/api/members/7")).andReturn().getResponse();

        // Then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString().contains("phil")).isTrue();
        assertThat(response.getContentAsString().contains("webb")).isTrue();
    }

    @Test
    void findById_not_found() throws Exception {
        // When
        Mockito.when(memberService.findById(7)).thenReturn(Optional.empty());
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.get("/api/members/7")).andReturn().getResponse();

        // Then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

    @Test
    void deleteMember() throws Exception {
        // Given
        Member member = new Member();
        member.setFirstname("phil");
        member.setLastname("webb");

        Mockito.when(memberService.findById(7)).thenReturn(Optional.of(member));

        //When
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.delete("/api/members/7")).andReturn().getResponse();

        // Then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void updateUser() throws Exception {
        Member member = new Member();
        member.setFirstname("phil");
        member.setLastname("webb");
        member.setEmail("webb@gmail.com");

        Member member1 = new Member();
        member1.setFirstname("aaaa");
        member1.setLastname("aaaaa");
        member1.setEmail("aaaaa");

        memberService.save(member1);

        Mockito.when(memberService.findById(4)).thenReturn(Optional.of(member1));
        Mockito.when(memberService.updateMember(Mockito.any(Integer.class), Mockito.any(Member.class))).thenReturn(member);

        MockHttpServletResponse response = mockMvc.perform(
                MockMvcRequestBuilders.put("/api/members/4").contentType(MediaType.APPLICATION_JSON).content(asJsonString(member))).andReturn().getResponse();

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
