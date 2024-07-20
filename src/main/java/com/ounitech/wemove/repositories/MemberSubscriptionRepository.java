package com.ounitech.wemove.repositories;

import com.ounitech.wemove.models.Member;
import com.ounitech.wemove.models.MemberSubscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberSubscriptionRepository extends JpaRepository<MemberSubscription, Integer> {

    Optional<MemberSubscription> findBymember(Member member);

}
