package com.ounitech.wemove.controllers;

import com.ounitech.wemove.models.Member;
import com.ounitech.wemove.models.MemberSubscription;
import com.ounitech.wemove.models.Subscription;
import com.ounitech.wemove.services.MemberService;
import com.ounitech.wemove.services.MemberSubscriptionService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
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
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MemberSubscriptionController.class)
class MemberSubscriptionControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MemberSubscriptionService memberSubscriptionService;

    @MockBean
    private MemberService memberService;

    @Test
    void findMemberSubscriptionTest() throws Exception {
        //Given
        Member member = new Member();
        member.setId(1000);

        MemberSubscription memberSubscription = new MemberSubscription();
        memberSubscription.setId(1000);
        memberSubscription.setMember(member);

        //When
        Mockito.when(memberService.findById(1000)).thenReturn(Optional.of(member));
        Mockito.when(memberSubscriptionService.findMemberSubscription(Mockito.any(Member.class))).thenReturn(Optional.of(memberSubscription));


        MockHttpServletResponse response = mockMvc.perform(
                MockMvcRequestBuilders.get("/api/MemberSubscriptions/1000")).andReturn().getResponse();

        //Then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void subscribeTest() throws Exception {
        //Given
        Member member = new Member();
        member.setId(1000);

        Subscription subscription = new Subscription();
        subscription.setSubscriptionName("GOLD");

        MemberSubscription memberSubscription = new MemberSubscription();
        memberSubscription.setId(1000);
        memberSubscription.setMember(member);

        //When
        Mockito.when(memberService.findById(1000)).thenReturn(Optional.of(member));
        Mockito.when(memberSubscriptionService.findMemberSubscription(Mockito.any(Member.class))).thenReturn(Optional.empty());
        Mockito.when(memberSubscriptionService.subscribe(1000, "GOLD")).thenReturn(memberSubscription);

        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.post("/api/MemberSubscriptions/subscribe")
                .param("id", "1000")
                .param("subscriptionName", "GOLD")).andReturn().getResponse();

        //Then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testSubscribe_InvalidSubscriptionName() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.post("/api/MemberSubscriptions/subscribe")
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

        Mockito.when(memberService.findById(1)).thenReturn(Optional.of(member));
        Mockito.when(memberSubscriptionService.findMemberSubscription(member)).thenReturn(Optional.of(memberSubscription));

        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.delete("/api/MemberSubscriptions/1")).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }
}