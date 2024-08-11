package com.ounitech.wemove.repositories;

import com.ounitech.wemove.models.Subscription;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class SubscriptionRepositoryTest {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @BeforeEach
    void setUp() {
        Subscription goldSub = new Subscription();
        goldSub.setName("GOLD");
        goldSub.setPrice(300);
        goldSub.setDuration(Subscription.Duration.year);
        goldSub.setActive(true);

        Subscription silverSub = new Subscription();
        silverSub.setName("SILVER");
        silverSub.setPrice(30);
        silverSub.setDuration(Subscription.Duration.monthly);
        silverSub.setActive(true);

        Subscription bronzeSub = new Subscription();
        bronzeSub.setName("BRONZE");
        bronzeSub.setPrice(300);
        bronzeSub.setDuration(Subscription.Duration.daily);
        bronzeSub.setActive(true);

        subscriptionRepository.saveAll(List.of(goldSub, silverSub, bronzeSub));
    }

    @Test
    void findByName() {
        // Given // When
        Optional<Subscription> result = subscriptionRepository.findByName("GOLD");

        // Then
        assertThat(result)
                .isPresent().get()
                .extracting(Subscription::getName)
                .isEqualTo("GOLD");
    }
}