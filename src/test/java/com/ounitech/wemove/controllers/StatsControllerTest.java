package com.ounitech.wemove.controllers;

import com.ounitech.wemove.services.StatsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StatsController.class)
class StatsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StatsService statsService;

    @Test
    void testGetMembersCount() throws Exception {
        when(statsService.getMembersCount()).thenReturn(50L);

        mockMvc.perform(get("/api/stats/members/count"))
                .andExpect(status().isOk())
                .andExpect(content().string("50"));
    }

    @Test
    void testGetActiveMembersCount() throws Exception {
        when(statsService.getActiveMembersCount()).thenReturn(50L);

        mockMvc.perform(get("/api/stats/active-members/count"))
                .andExpect(status().isOk())
                .andExpect(content().string("50"));
    }

    @Test
    void testGetInactiveMembersCount() throws Exception {
        when(statsService.getInactiveMembersCount()).thenReturn(50L);

        mockMvc.perform(get("/api/stats/inactive-members/count"))
                .andExpect(status().isOk())
                .andExpect(content().string("50"));
    }

    @Test
    void testGetGoldMembersCount() throws Exception {
        when(statsService.getGoldMembersCount()).thenReturn(50L);

        mockMvc.perform(get("/api/stats/GOLD-subscriptions/count"))
                .andExpect(status().isOk())
                .andExpect(content().string("50"));
    }

    @Test
    void testGetSilverMembersCount() throws Exception {
        when(statsService.getSilverMembersCount()).thenReturn(50L);

        mockMvc.perform(get("/api/stats/SILVER-subscriptions/count"))
                .andExpect(status().isOk())
                .andExpect(content().string("50"));
    }

    @Test
    void testGetBronzeMembersCount() throws Exception {
        when(statsService.getBronzeMembersCount()).thenReturn(50L);

        mockMvc.perform(get("/api/stats/BRONZE-subscriptions/count"))
                .andExpect(status().isOk())
                .andExpect(content().string("50"));
    }

    @Test
    void testGetMaleMembersCount() throws Exception {
        when(statsService.getMaleMembersCount()).thenReturn(50L);

        mockMvc.perform(get("/api/stats/male-members/count"))
                .andExpect(status().isOk())
                .andExpect(content().string("50"));
    }

    @Test
    void testGetFemaleMembersCount() throws Exception {
        when(statsService.getFemaleMembersCount()).thenReturn(50L);

        mockMvc.perform(get("/api/stats/female-members/count"))
                .andExpect(status().isOk())
                .andExpect(content().string("50"));
    }
}