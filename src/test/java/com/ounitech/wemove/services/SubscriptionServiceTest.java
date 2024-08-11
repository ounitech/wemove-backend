package com.ounitech.wemove.services;

import com.ounitech.wemove.models.Subscription;
import com.ounitech.wemove.repositories.SubscriptionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class SubscriptionServiceTest {

    @Mock
    private SubscriptionRepository subscriptionRepository;

    @InjectMocks
    private SubscriptionService subscriptionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void save() {
        // Given
        Subscription input = new Subscription();

        Subscription saved = new Subscription();
        saved.setId(1);

        // When
        when(subscriptionRepository.save(input)).thenReturn(saved);

        Subscription result = subscriptionService.save(input);

        // Then
        assertThat(result)
                .isNotNull()
                .extracting(Subscription::getId)
                .isEqualTo(1);

        verify(subscriptionRepository, times(1)).save(input);
    }

    @Test
    void findById() {
        // Given
        Subscription sub = new Subscription();
        sub.setId(1);

        when(subscriptionRepository.findById(1)).thenReturn(Optional.of(sub));

        // When
        Optional<Subscription> result = subscriptionService.findById(1);

        // Then
        assertThat(result)
                .isNotEmpty()
                .get()
                .extracting(Subscription::getId).isEqualTo(1);
        verify(subscriptionRepository, times(1)).findById(1);
    }

    @Test
    void findAll() {
        // Given
        Subscription sub1 = new Subscription();
        Subscription sub2 = new Subscription();
        Subscription sub3 = new Subscription();

        when(subscriptionRepository.findAll()).thenReturn(List.of(sub1, sub2, sub3));
        // When
        List<Subscription> result = subscriptionService.findAll();
        // Then
        assertThat(result)
                .hasSize(3);
        verify(subscriptionRepository, times(1)).findAll();
    }
}