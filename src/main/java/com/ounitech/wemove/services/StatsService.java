package com.ounitech.wemove.services;

import com.ounitech.wemove.models.Gender;
import com.ounitech.wemove.models.StatSummary;
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

    public StatSummary findStatSummary() {

        StatSummary statSummary = new StatSummary();

        long totalMembers = memberRepository.count();
        long activeMembers = memberRepository.countActiveMembers();
        long inactiveMembers = memberRepository.countInactiveMembers();
        long goldMembers = memberSubscriptionRepository.countGoldMembers();
        long silverMembers = memberSubscriptionRepository.countSilverMembers();
        long bronzeMembers = memberSubscriptionRepository.countBronzeMembers();
        long maleMembers = memberRepository.countByGender(Gender.Male);
        long femaleMembers = memberRepository.countByGender(Gender.Female);

        statSummary.setTotalMemberCount(totalMembers);
        statSummary.setActiveMemberCount(activeMembers);
        statSummary.setInactiveMemberCount(inactiveMembers);
        statSummary.setGoldMemberCount(goldMembers);
        statSummary.setSilverMemberCount(silverMembers);
        statSummary.setBronzeMemberCount(bronzeMembers);
        statSummary.setMaleMemberCount(maleMembers);
        statSummary.setFemaleMemberCount(femaleMembers);

        return statSummary;
    }

}
