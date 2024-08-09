package com.ounitech.wemove.repositories;

import com.ounitech.wemove.models.Subscription;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
class SubscriptionRepositoryTest {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void findBySubscriptionName() {
        // Given // When
        Optional<Subscription> result = subscriptionRepository.findBySubscriptionName("GOLD");

        // Then
        Assertions.assertThat(result)
                .isPresent().get()
                .extracting(Subscription::getSubscriptionName)
                .isEqualTo("GOLD");
    }
}