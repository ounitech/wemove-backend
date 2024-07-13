package com.ounitech.wemove.repositories;

import com.ounitech.wemove.models.Member;
import com.ounitech.wemove.models.MemberSubscription;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberSubscriptionRepository extends JpaRepository<MemberSubscription, Integer> {

    Optional<MemberSubscription> findBymember(Member member);

}
