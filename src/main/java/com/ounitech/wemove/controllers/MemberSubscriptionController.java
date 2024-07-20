package com.ounitech.wemove.controllers;


import com.ounitech.wemove.models.Member;
import com.ounitech.wemove.models.MemberSubscription;
import com.ounitech.wemove.repositories.MemberRepository;
import com.ounitech.wemove.services.MemberService;
import com.ounitech.wemove.services.MemberSubscriptionService;
import com.ounitech.wemove.services.SubscriptionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;


@RestController
@RequestMapping("/api/MemberSubscriptions")
public class MemberSubscriptionController {

    private final MemberSubscriptionService memberSubscriptionService;
    private final MemberService memberService;

    public MemberSubscriptionController(MemberSubscriptionService memberSubscriptionService, MemberRepository memberRepository, MemberService memberService, SubscriptionService subscriptionService) {
        this.memberSubscriptionService = memberSubscriptionService;
        this.memberService = memberService;
    }

    @PostMapping("/subscribe")
    public ResponseEntity<MemberSubscription> subscribe(@RequestParam Integer id, @RequestParam String subscriptionName) {

        if (!Objects.equals(subscriptionName, "GOLD") && !Objects.equals(subscriptionName, "SILVER") && !Objects.equals(subscriptionName, "BRONZE"))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        Optional<Member> memberById = memberService.findById(id);


        if (memberById.isPresent()) {
            if (memberSubscriptionService.findMember(memberById.get()).isPresent()) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
            return new ResponseEntity<>(memberSubscriptionService.subscribe(id, subscriptionName), HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
