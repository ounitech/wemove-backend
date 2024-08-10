package com.ounitech.wemove.services;

import com.ounitech.wemove.repositories.SubscriptionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
        // When
        // Then
    }

    @Test
    void findById() {
        // Given
        // When
        // Then
    }

    @Test
    void findAll() {
        // Given
        // When
        // Then
    }
}