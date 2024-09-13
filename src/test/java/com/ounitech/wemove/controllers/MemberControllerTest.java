package com.ounitech.wemove.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ounitech.wemove.Validator.MemberValidator;
import com.ounitech.wemove.models.Member;
import com.ounitech.wemove.models.MemberSubscription;
import com.ounitech.wemove.models.Subscription;
import com.ounitech.wemove.services.MemberService;
import com.ounitech.wemove.services.MemberSubscriptionService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.validation.Errors;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@WebMvcTest(MemberController.class)
class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MemberService memberService;

    @MockBean
    private MemberSubscriptionService memberSubscriptionService;

    @MockBean
    private MemberValidator memberValidator;

    @Test
    void saveMemberTest() throws Exception {
        Member member = new Member();
        member.setId(1000);
        member.setFirstname("saad");
        member.setLastname("bguir");
        member.setEmail("sa@gmail.com");

        when(memberService.findByEmail(any(String.class))).thenReturn(null);
        when(memberService.save(member)).thenReturn(member);
        doNothing().when(memberValidator).validate(any(Member.class), any(Errors.class));


        MockHttpServletResponse response = mockMvc.perform(
                MockMvcRequestBuilders.post("/api/members/save").contentType(MediaType.APPLICATION_JSON).content(asJsonString(member))).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.getContentAsString().contains("saad")).isTrue();
        assertThat(response.getContentAsString().contains("bguir")).isTrue();
        assertThat(response.getContentAsString().contains("sa@gmail.com")).isTrue();
    }

    @Test
    void saveMemberTest_DuplicateEmail() throws Exception {
        Member member = new Member();
        member.setId(1000);
        member.setFirstname("saad");
        member.setLastname("bguir");
        member.setEmail("saad@gmail.com");

        Member member2 = new Member();
        member2.setEmail("saad@gmail.com");

        when(memberService.findByEmail(any(String.class))).thenReturn(member2);

        MockHttpServletResponse response = mockMvc.perform(
                MockMvcRequestBuilders.post("/api/members/save").contentType(MediaType.APPLICATION_JSON).content(asJsonString(member))).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CONFLICT.value());
    }

    @Test
    void saveMemberTest_InvalidEmail() throws Exception {
        Member member = new Member();
        member.setId(1000);
        member.setFirstname("firstname");
        member.setLastname("lastname");
        member.setEmail("email.com");

        when(memberService.findByEmail(any(String.class))).thenReturn(null);

        doAnswer(invocation -> {
            Errors errors = invocation.getArgument(1);
            errors.rejectValue("email", "not a well-formed email address");
            return null;
        }).when(memberValidator).validate(any(Member.class), any(Errors.class));

        MockHttpServletResponse response = mockMvc.perform(
                MockMvcRequestBuilders.post("/api/members/save").contentType(MediaType.APPLICATION_JSON).content(asJsonString(member))).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void saveMemberTest_missing_fields() throws Exception {
        //Given
        Member member = new Member();
        member.setId(1000);
        member.setEmail("saad@gmail.com");

        when(memberService.findByEmail(any(String.class))).thenReturn(null);

        doAnswer(invocation -> {
            Errors errors = invocation.getArgument(1);
            errors.rejectValue("firstname", "firstname is required");
            errors.rejectValue("lastname", "lastname is required");
            errors.rejectValue("email", "email is required");
            return null;
        }).when(memberValidator).validate(any(Member.class), any(Errors.class));

        // When
        MockHttpServletResponse response = mockMvc.perform(
                MockMvcRequestBuilders.post("/api/members/save").contentType(MediaType.APPLICATION_JSON).content(asJsonString(member))).andReturn().getResponse();

        // Then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void saveMemberTest_Invalid_firstname() throws Exception {
        //Given
        Member member = new Member();
        member.setId(1000);
        member.setFirstname("mo");
        member.setLastname("salah");
        member.setEmail("mo@gmail.com");

        when(memberService.findByEmail(any(String.class))).thenReturn(null);

        doAnswer(invocation -> {
            Errors errors = invocation.getArgument(1);
            errors.rejectValue("firstname", "must contain only alphabetic characters and spaces");
            return null;
        }).when(memberValidator).validate(any(Member.class), any(Errors.class));

        // When
        MockHttpServletResponse response = mockMvc.perform(
                MockMvcRequestBuilders.post("/api/members/save").contentType(MediaType.APPLICATION_JSON).content(asJsonString(member))).andReturn().getResponse();

        // Then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void findById_nominal_case() throws Exception {
        // Given
        Member member = new Member();
        member.setFirstname("phil");
        member.setLastname("webb");

        when(memberService.findById(7)).thenReturn(Optional.of(member));

        // When
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.get("/api/members/findById/7")).andReturn().getResponse();

        // Then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString().contains("phil")).isTrue();
        assertThat(response.getContentAsString().contains("webb")).isTrue();
    }

    @Test
    void findById_not_found() throws Exception {
        // When
        when(memberService.findById(7)).thenReturn(Optional.empty());
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.get("/api/members/findById/7")).andReturn().getResponse();

        // Then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

    @Test
    void deleteMember() throws Exception {
        // Given
        Member member = new Member();
        member.setFirstname("phil");
        member.setLastname("webb");

        when(memberService.findById(7)).thenReturn(Optional.of(member));

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


        when(memberService.findById(any(Integer.class))).thenReturn(Optional.of(member1));
        when(memberService.updateMember(any(Integer.class), any(Member.class))).thenReturn(member);

        MockHttpServletResponse response = mockMvc.perform(
                MockMvcRequestBuilders.put("/api/members/update/4").contentType(MediaType.APPLICATION_JSON).content(asJsonString(member))).andReturn().getResponse();

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

    @Test
    void activateUserTest() throws Exception {
        Member member = new Member();
        member.setFirstname("phil");
        member.setLastname("webb");
        member.setEmail("webb@gmail.com");
        member.setActive(false);
        member.setId(1000);

        Member activatedMember = new Member();
        member.setFirstname("phil");
        member.setLastname("webb");
        member.setEmail("webb@gmail.com");
        member.setActive(true);
        member.setId(1000);

        when(memberService.findById(any(Integer.class))).thenReturn(Optional.of(member));
        when(memberService.activateMember(1000)).thenReturn(activatedMember);

        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.put("/api/members/activate/1000"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void findMembersWithPagination_nominal_case() throws Exception {
        // Given
        Member member1 = new Member();
        member1.setFirstname("phil");
        member1.setLastname("webb");
        member1.setId(1000);

        Member member2 = new Member();
        member2.setFirstname("john");
        member2.setLastname("doe");
        member2.setId(1001);

        List<Member> members = Arrays.asList(member1, member2);
        Page<Member> memberPage = new PageImpl<>(members);

        when(memberService.findMembersWithPagination(anyInt())).thenReturn(memberPage);

        // When
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.get("/api/members/page/1"))
                .andReturn().getResponse();

        // Then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).contains("phil");
        assertThat(response.getContentAsString()).contains("webb");
        assertThat(response.getContentAsString()).contains("john");
        assertThat(response.getContentAsString()).contains("doe");
    }

    @Test
    void findMembersWithPagination_empty_page() throws Exception {
        // Given
        Page<Member> emptyPage = Page.empty();

        when(memberService.findMembersWithPagination(anyInt())).thenReturn(emptyPage);

        // When
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.get("/api/members/page/1"))
                .andReturn().getResponse();

        // Then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).doesNotContain("phil");
        assertThat(response.getContentAsString()).doesNotContain("webb");
        assertThat(response.getContentAsString()).doesNotContain("john");
        assertThat(response.getContentAsString()).doesNotContain("doe");
    }


    @Test
    void findMemberSubscriptionTest() throws Exception {
        //Given
        Member member = new Member();
        member.setId(1000);

        MemberSubscription memberSubscription = new MemberSubscription();
        memberSubscription.setId(1000);
        memberSubscription.setMember(member);

        //When
        when(memberService.findById(1000)).thenReturn(Optional.of(member));
        when(memberSubscriptionService.findMemberSubscription(Mockito.any(Member.class))).thenReturn(Optional.of(memberSubscription));


        MockHttpServletResponse response = mockMvc.perform(
                MockMvcRequestBuilders.get("/api/members/get-member-subscription/1000")).andReturn().getResponse();

        //Then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void subscribeTest() throws Exception {
        //Given
        Member member = new Member();
        member.setId(1000);

        Subscription subscription = new Subscription();
        subscription.setName("GOLD");

        MemberSubscription memberSubscription = new MemberSubscription();
        memberSubscription.setId(1000);
        memberSubscription.setMember(member);

        //When
        when(memberService.findById(1000)).thenReturn(Optional.of(member));
        when(memberSubscriptionService.findMemberSubscription(Mockito.any(Member.class))).thenReturn(Optional.empty());
        when(memberSubscriptionService.subscribe(1000, "GOLD")).thenReturn(memberSubscription);

        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.post("/api/members/subscribe")
                .param("id", "1000")
                .param("subscriptionName", "GOLD")).andReturn().getResponse();

        //Then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testSubscribe_InvalidSubscriptionName() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.post("/api/members/subscribe")
                .param("id", "1000")
                .param("subscriptionName", "hgfhgfh")).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());

    }

    @Test
    void DeleteMemberSubscriptionTest() throws Exception {
        Member member = new Member();
        member.setId(1);

        MemberSubscription memberSubscription = new MemberSubscription();
        memberSubscription.setId(1);
        memberSubscription.setMember(member);

        when(memberService.findById(1)).thenReturn(Optional.of(member));
        when(memberSubscriptionService.findMemberSubscription(member)).thenReturn(Optional.of(memberSubscription));

        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.delete("/api/members/delete-member-subscription/1")).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }
}

