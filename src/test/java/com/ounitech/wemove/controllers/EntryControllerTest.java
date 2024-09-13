package com.ounitech.wemove.controllers;

import com.ounitech.wemove.models.Entry;
import com.ounitech.wemove.services.EntryService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EntryController.class)
class EntryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EntryService entryService;


    @Test
    void findAllEntriesTest() throws Exception {
        //Given
        Entry entry1 = new Entry();
        entry1.setId(1);
        entry1.setEntryTime(LocalDateTime.parse("2024-07-30T16:58:00"));
        entry1.setLeaveTime(LocalDateTime.parse("2024-07-30T17:58:00"));

        Entry entry2 = new Entry();
        entry2.setId(2);
        entry2.setEntryTime(LocalDateTime.parse("2025-07-30T10:00:00"));
        entry2.setLeaveTime(LocalDateTime.parse("2025-07-30T12:00:00"));

        List<Entry> entries = Arrays.asList(entry1, entry2);
        when(entryService.findAllEntries()).thenReturn(entries);

        //When //Then
        mockMvc.perform(get("/api/entries/findAll")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].entryTime").value("2024-07-30T16:58:00"))
                .andExpect(jsonPath("$[0].leaveTime").value("2024-07-30T17:58:00"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].entryTime").value("2025-07-30T10:00:00"))
                .andExpect(jsonPath("$[1].leaveTime").value("2025-07-30T12:00:00"));
    }

    @Test
    void createEntryTest() throws Exception {
        //Given
        Entry savedEntry = new Entry();
        savedEntry.setId(1);
        savedEntry.setEntryTime(LocalDateTime.parse("2024-07-30T16:58:00"));
        savedEntry.setLeaveTime(LocalDateTime.parse("2024-07-30T17:58:00"));

        Mockito.when(entryService.createEntry(any(Entry.class))).thenReturn(savedEntry);

        String inputJson = """
                    {
                        "id": "1",
                        "entryTime": "2024-07-30T16:58:00",
                        "leaveTime": "2024-07-30T17:58:00"
                    }
                """;

        //When and Then
        mockMvc.perform(post("/api/entries")
                        .contentType(MediaType.APPLICATION_JSON).content(inputJson))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.entryTime").value("2024-07-30T16:58:00"))
                .andExpect(jsonPath("$.leaveTime").value("2024-07-30T17:58:00"));
    }

    @Test
    void findEntriesByMemberTest() throws Exception {
        //Given
        Entry entry1 = new Entry();
        entry1.setId(1);
        entry1.setEntryTime(LocalDateTime.parse("2024-07-30T16:58:00"));
        entry1.setLeaveTime(LocalDateTime.parse("2024-07-30T17:58:00"));

        Entry entry2 = new Entry();
        entry2.setId(2);
        entry2.setEntryTime(LocalDateTime.parse("2025-07-30T10:00:00"));
        entry2.setLeaveTime(LocalDateTime.parse("2025-07-30T12:00:00"));

        when(entryService.findEntriesByMember(any(Integer.class))).thenReturn(List.of(entry1, entry2));

        //When and Then
        mockMvc.perform(get("/api/entries/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].entryTime").value("2024-07-30T16:58:00"))
                .andExpect(jsonPath("$[0].leaveTime").value("2024-07-30T17:58:00"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].entryTime").value("2025-07-30T10:00:00"))
                .andExpect(jsonPath("$[1].leaveTime").value("2025-07-30T12:00:00"));
    }
}