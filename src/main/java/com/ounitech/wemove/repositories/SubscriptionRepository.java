package com.ounitech.wemove.repositories;

import com.ounitech.wemove.models.Member;
import com.ounitech.wemove.models.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {

    public Subscription findBysubscriptionName(String subscriptionName);

}
