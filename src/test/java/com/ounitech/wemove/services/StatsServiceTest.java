package com.ounitech.wemove.services;

import com.ounitech.wemove.repositories.MemberRepository;
import com.ounitech.wemove.repositories.MemberSubscriptionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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
        // Given // When
        Mockito.when(memberRepository.count()).thenReturn(5L);
        long membersCount = statsService.getMembersCount();


        // Then
        assertThat(membersCount).isEqualTo(5);
        verify(memberRepository, times(1)).count();
    }

    @Test
    void getActiveMembersCount() {
        // Given // When
        Mockito.when(memberRepository.countActiveMembers()).thenReturn(5L);
        long activeMembersCount = statsService.getActiveMembersCount();

        // Then
        assertThat(activeMembersCount).isEqualTo(5);
        verify(memberRepository, times(1)).countActiveMembers();

    }

    @Test
    void getInactiveMembersCount() {
        // Given // When
        Mockito.when(memberRepository.countInactiveMembers()).thenReturn(5L);
        long inactiveMembersCount = statsService.getInactiveMembersCount();

        // Then
        assertThat(inactiveMembersCount).isEqualTo(5);
        verify(memberRepository, times(1)).countInactiveMembers();
    }

    @Test
    void getGoldMembersCount() {
        // Given // When
        Mockito.when(memberSubscriptionRepository.countGoldMembers()).thenReturn(5L);
        long goldMembersCount = statsService.getGoldMembersCount();

        // Then
        assertThat(goldMembersCount).isEqualTo(5);
        verify(memberSubscriptionRepository, times(1)).countGoldMembers();
    }

    @Test
    void getSilverMembersCount() {
        // Given // When
        Mockito.when(memberSubscriptionRepository.countSilverMembers()).thenReturn(5L);
        long silverMembersCount = statsService.getSilverMembersCount();

        // Then
        assertThat(silverMembersCount).isEqualTo(5);
        verify(memberSubscriptionRepository, times(1)).countSilverMembers();
    }

    @Test
    void getBronzeMembersCount() {
        // Given // When
        Mockito.when(memberSubscriptionRepository.countBronzeMembers()).thenReturn(5L);
        long bronzeMembersCount = statsService.getBronzeMembersCount();

        // Then
        assertThat(bronzeMembersCount).isEqualTo(5);
        verify(memberSubscriptionRepository, times(1)).countBronzeMembers();
    }

    @Test
    void getMaleMembersCount() {
        // Given // When
        Mockito.when(memberRepository.countByGender("male")).thenReturn(5L);
        long maleMembersCount = statsService.getMaleMembersCount();

        // Then
        assertThat(maleMembersCount).isEqualTo(5);
        verify(memberRepository, times(1)).countByGender("male");
    }

    @Test
    void getFemaleMembersCount() {
        // Given // When
        Mockito.when(memberRepository.countByGender("female")).thenReturn(5L);
        long femaleMembersCount = statsService.getFemaleMembersCount();

        // Then
        assertThat(femaleMembersCount).isEqualTo(5);
        verify(memberRepository, times(1)).countByGender("female");
    }
}