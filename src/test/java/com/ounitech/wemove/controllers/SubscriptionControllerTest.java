package com.ounitech.wemove.controllers;

import com.ounitech.wemove.models.Subscription;
import com.ounitech.wemove.services.SubscriptionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SubscriptionController.class)
class SubscriptionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SubscriptionService subscriptionService;

    @Test
    void testFindAllSubscriptions() throws Exception {
        //Given
        Subscription subscription1 = new Subscription();
        Subscription subscription2 = new Subscription();

        when(subscriptionService.findAll()).thenReturn(List.of(subscription1, subscription2));

        //When //Then
        mockMvc.perform(get("/api/subscriptions").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}