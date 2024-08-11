package com.ounitech.wemove.services;

import com.ounitech.wemove.repositories.MemberRepository;
import com.ounitech.wemove.repositories.MemberSubscriptionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

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
        when(memberRepository.count()).thenReturn(5L);

        // When
        long membersCount = statsService.getMembersCount();

        // Then
        assertThat(membersCount).isEqualTo(5);
        verify(memberRepository, times(1)).count();
    }

    @Test
    void getActiveMembersCount() {
        // Given
        when(memberRepository.countActiveMembers()).thenReturn(5L);

        // When
        long activeMembersCount = statsService.getActiveMembersCount();

        // Then
        assertThat(activeMembersCount).isEqualTo(5);
        verify(memberRepository, times(1)).countActiveMembers();
    }

    @Test
    void getInactiveMembersCount() {
        // Given
        when(memberRepository.countInactiveMembers()).thenReturn(5L);

        // When
        long inactiveMembersCount = statsService.getInactiveMembersCount();

        // Then
        assertThat(inactiveMembersCount).isEqualTo(5);
        verify(memberRepository, times(1)).countInactiveMembers();
    }

    @Test
    void getGoldMembersCount() {
        // Given
        when(memberSubscriptionRepository.countGoldMembers()).thenReturn(5L);

        // When
        long goldMembersCount = statsService.getGoldMembersCount();

        // Then
        assertThat(goldMembersCount).isEqualTo(5);
        verify(memberSubscriptionRepository, times(1)).countGoldMembers();
    }

    @Test
    void getSilverMembersCount() {
        // Given
        when(memberSubscriptionRepository.countSilverMembers()).thenReturn(5L);

        // When
        long silverMembersCount = statsService.getSilverMembersCount();

        // Then
        assertThat(silverMembersCount).isEqualTo(5);
        verify(memberSubscriptionRepository, times(1)).countSilverMembers();
    }

    @Test
    void getBronzeMembersCount() {
        // Given
        when(memberSubscriptionRepository.countBronzeMembers()).thenReturn(5L);

        // When
        long bronzeMembersCount = statsService.getBronzeMembersCount();

        // Then
        assertThat(bronzeMembersCount).isEqualTo(5);
        verify(memberSubscriptionRepository, times(1)).countBronzeMembers();
    }

    @Test
    void getMaleMembersCount() {
        // Given
        when(memberRepository.countByGender("male")).thenReturn(5L);

        // When
        long maleMembersCount = statsService.getMaleMembersCount();

        // Then
        assertThat(maleMembersCount).isEqualTo(5);
        verify(memberRepository, times(1)).countByGender("male");
    }

    @Test
    void getFemaleMembersCount() {
        // Given
        when(memberRepository.countByGender("female")).thenReturn(5L);

        // When
        long femaleMembersCount = statsService.getFemaleMembersCount();

        // Then
        assertThat(femaleMembersCount).isEqualTo(5);
        verify(memberRepository, times(1)).countByGender("female");
    }
}