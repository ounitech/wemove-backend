package com.ounitech.wemove.controllers;

import com.ounitech.wemove.models.StatSummary;
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
    void testFindStatSummary() throws Exception {
        StatSummary statSummary = new StatSummary();
        statSummary.setTotalMemberCount(50);
        statSummary.setActiveMemberCount(45);
        statSummary.setInactiveMemberCount(5);
        statSummary.setMaleMemberCount(30);
        statSummary.setFemaleMemberCount(20);

        when(statsService.findStatSummary())
                .thenReturn(statSummary);

        mockMvc.perform(get("/api/stats"))
                .andExpect(status().isOk())
                .andExpect(content().string("50"));
    }
}