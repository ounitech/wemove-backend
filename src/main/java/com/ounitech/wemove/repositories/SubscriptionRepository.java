package com.ounitech.wemove.repositories;

import com.ounitech.wemove.models.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {

    Subscription findBysubscriptionName(String subscriptionName);
}
