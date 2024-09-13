package com.ounitech.wemove.services;


import com.ounitech.wemove.models.Member;
import com.ounitech.wemove.models.MemberSubscription;
import com.ounitech.wemove.models.Subscription;
import com.ounitech.wemove.repositories.MemberRepository;
import com.ounitech.wemove.repositories.MemberSubscriptionRepository;
import com.ounitech.wemove.repositories.SubscriptionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MemberSubscriptionService {

    private final MemberSubscriptionRepository memberSubscriptionRepository;
    private final MemberRepository memberRepository;
    private final SubscriptionRepository subscriptionRepository;


    public MemberSubscriptionService(MemberSubscriptionRepository memberSubscriptionRepository, MemberRepository memberRepository, SubscriptionRepository subscriptionRepository, MemberService memberService) {
        this.memberSubscriptionRepository = memberSubscriptionRepository;
        this.memberRepository = memberRepository;
        this.subscriptionRepository = subscriptionRepository;
    }

    public List<MemberSubscription> findAll() {
        return memberSubscriptionRepository.findAll();
    }

    public Optional<MemberSubscription> findMemberSubscription(Member member) {
        return memberSubscriptionRepository.findBymember(member);
    }

    public MemberSubscription subscribe(Integer id, String subscriptionName) {
        Optional<Member> memberById = memberRepository.findById(id);
        Member member = memberById.get();

        member.setActive(true);

        Subscription subscriptionByName = subscriptionRepository.findByName(subscriptionName).orElse(null);

        MemberSubscription memberSubscription = new MemberSubscription();
        memberSubscription.setSubscription(subscriptionByName);
        memberSubscription.setMember(member);
        memberSubscription.setPaid(subscriptionByName.getPrice());
        memberSubscription.setStartDate(LocalDate.from(LocalDateTime.now()));

        return memberSubscriptionRepository.save(memberSubscription);
    }

    public void deleteMemberSubscription(Integer id) {
        memberSubscriptionRepository.deleteById(id);
    }
}
