package com.ounitech.wemove.services;

import com.ounitech.wemove.models.Entry;
import com.ounitech.wemove.repositories.EntryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class EntryServiceTest {

    @Mock
    private EntryRepository entryRepository;

    @InjectMocks
    private EntryService entryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void createEntry() {
        // Given
        Entry input = new Entry();

        Entry savedEntry = new Entry();
        savedEntry.setId(1);

        when(entryRepository.save(input)).thenReturn(savedEntry);

        // When
        Entry result = entryService.createEntry(input);

        // Then
        assertThat(result)
                .isNotNull()
                .extracting(Entry::getId)
                .isEqualTo(1);
    }

    @Test
    void findAllEntries() {
        // Given
        Entry entryOne = new Entry();
        entryOne.setId(1);

        Entry entryTwo = new Entry();
        entryTwo.setId(2);

        Entry entryThree = new Entry();
        entryThree.setId(3);

        when(entryRepository.findAll())
                .thenReturn(List.of(entryOne, entryTwo, entryThree));

        // When
        List<Entry> results = entryService.findAllEntries();

        // Then
        assertThat(results)
                .hasSize(3)
                .extracting(Entry::getId)
                .containsExactlyInAnyOrder(1, 2, 3);
    }

    @Test
    void findEntriesByMember() {
        // Given
        Integer memberId = 1;

        Entry entryOne = new Entry();
        entryOne.setId(1);

        Entry entryTwo = new Entry();
        entryTwo.setId(2);

        when(entryRepository.findByMemberId(memberId))
                .thenReturn(List.of(entryOne, entryTwo));

        // When
        List<Entry> results = entryService.findEntriesByMember(memberId);

        // Then
        assertThat(results)
                .hasSize(2)
                .extracting(Entry::getId)
                .containsExactlyInAnyOrder(1, 2);
    }
}