package com.ounitech.wemove.services;

import com.ounitech.wemove.repositories.MemberRepository;
import com.ounitech.wemove.repositories.MemberSubscriptionRepository;
import org.springframework.stereotype.Service;


@Service
public class StatsService {

    private final MemberRepository memberRepository;
    private final MemberSubscriptionRepository memberSubscriptionRepository;

    public StatsService(MemberRepository memberRepository, MemberSubscriptionRepository memberSubscriptionRepository) {
        this.memberRepository = memberRepository;
        this.memberSubscriptionRepository = memberSubscriptionRepository;
    }

    public long getMembersCount() {
        return memberRepository.count();
    }

    public long getActiveMembersCount() {
        return memberRepository.countActiveMembers();
    }

    public long getInactiveMembersCount() {
        return memberRepository.countInactiveMembers();
    }

    public long getGoldMembersCount() {
        return memberSubscriptionRepository.countGoldMembers();
    }

    public long getSilverMembersCount() {
        return memberSubscriptionRepository.countSilverMembers();
    }

    public long getBronzeMembersCount() {
        return memberSubscriptionRepository.countBronzeMembers();
    }

    public long getMaleMembersCount() {
        return memberRepository.countByGender("male");
    }

    public long getFemaleMembersCount() {
        return memberRepository.countByGender("female");
    }


}
