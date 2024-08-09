package com.ounitech.wemove.repositories;

import com.ounitech.wemove.models.Member;
import com.ounitech.wemove.models.MemberSubscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MemberSubscriptionRepository extends JpaRepository<MemberSubscription, Integer> {

    Optional<MemberSubscription> findBymember(Member member);

    @Query("SELECT COUNT(m) FROM MemberSubscription m WHERE m.subscription.name = 'GOLD'")
    long countGoldMembers();

    @Query("SELECT COUNT(m) FROM MemberSubscription m WHERE m.subscription.name = 'SILVER'")
    long countSilverMembers();

    @Query("SELECT COUNT(m) FROM MemberSubscription m WHERE m.subscription.name = 'BRONZE'")
    long countBronzeMembers();
}
