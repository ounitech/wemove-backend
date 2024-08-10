package com.ounitech.wemove.services;

import com.ounitech.wemove.repositories.MemberRepository;
import com.ounitech.wemove.repositories.MemberSubscriptionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class StatsServiceTest {

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private MemberSubscriptionRepository memberSubscriptionRepository;

    @InjectMocks
    private StatsService statsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getMembersCount() {
        // Given
        // When
        // Then
    }

    @Test
    void getActiveMembersCount() {
        // Given
        // When
        // Then
    }

    @Test
    void getInactiveMembersCount() {
        // Given
        // When
        // Then
    }

    @Test
    void getGoldMembersCount() {
        // Given
        // When
        // Then
    }

    @Test
    void getSilverMembersCount() {
        // Given
        // When
        // Then
    }

    @Test
    void getBronzeMembersCount() {
        // Given
        // When
        // Then
    }

    @Test
    void getMaleMembersCount() {
        // Given
        // When
        // Then
    }

    @Test
    void getFemaleMembersCount() {
        // Given
        // When
        // Then
    }
}