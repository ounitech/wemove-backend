package com.ounitech.wemove.services;


import com.ounitech.wemove.models.Member;
import com.ounitech.wemove.models.MemberSubscription;
import com.ounitech.wemove.models.Subscription;
import com.ounitech.wemove.repositories.MemberRepository;
import com.ounitech.wemove.repositories.MemberSubscriptionRepository;
import com.ounitech.wemove.repositories.SubscriptionRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class MemberSubscriptionService {

    private final MemberSubscriptionRepository memberSubscriptionRepository;
    private final MemberRepository memberRepository;
    private final SubscriptionRepository subscriptionRepository;

    private final MemberService memberService;


    public MemberSubscriptionService(MemberSubscriptionRepository memberSubscriptionRepository, MemberRepository memberRepository, SubscriptionRepository subscriptionRepository, MemberService memberService) {
        this.memberSubscriptionRepository = memberSubscriptionRepository;
        this.memberRepository = memberRepository;
        this.subscriptionRepository = subscriptionRepository;
        this.memberService = memberService;
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

        member.setActive((byte) 1);

        Subscription subscriptionByName = subscriptionRepository.findBysubscriptionName(subscriptionName);

        MemberSubscription memberSubscription = new MemberSubscription();
        memberSubscription.setSubscription(subscriptionByName);
        memberSubscription.setMember(member);
        memberSubscription.setPaid(subscriptionByName.getSubscriptionPrice());
        memberSubscription.setStartDate(LocalDate.from(LocalDateTime.now()));

        return memberSubscriptionRepository.save(memberSubscription);
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void deactivateExpiredSubscription() {
        List<MemberSubscription> allMemberSubscription = memberSubscriptionRepository.findAll();

        for (MemberSubscription memberSubscription : allMemberSubscription) {
            LocalDate startDate = memberSubscription.getStartDate();
            Subscription.Duration subscriptionType = memberSubscription.getSubscription().getDuration();

            if (Objects.equals(subscriptionType, "daily")) {
                LocalDate endDate = startDate.plusDays(1);
                if (LocalDate.now().isAfter(endDate)) {
                    Member member = memberSubscription.getMember();
                    memberService.deactivateMember(member.getId());
                }

            } else if (Objects.equals(subscriptionType, "monthly")) {
                LocalDate endDate = startDate.plusDays(30);
                if (LocalDate.now().isAfter(endDate)) {
                    Member member = memberSubscription.getMember();
                    memberService.deactivateMember(member.getId());
                }
            } else {
                LocalDate endDate = startDate.plusDays(365);
                if (LocalDate.now().isAfter(endDate)) {
                    Member member = memberSubscription.getMember();
                    memberService.deactivateMember(member.getId());
                }
            }
        }
    }

    public void deleteMemberSubscription(Integer id) {
        memberSubscriptionRepository.deleteById(id);
    }
}
