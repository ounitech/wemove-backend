package com.ounitech.wemove.controllers;


import com.ounitech.wemove.models.Member;
import com.ounitech.wemove.models.MemberSubscription;
import com.ounitech.wemove.services.MemberService;
import com.ounitech.wemove.services.MemberSubscriptionService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;
    private final MemberSubscriptionService memberSubscriptionService;

    public MemberController(MemberService memberService, MemberSubscriptionService memberSubscriptionService) {
        this.memberService = memberService;
        this.memberSubscriptionService = memberSubscriptionService;
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Member> findById(@PathVariable("id") Integer id) {
        Optional<Member> member = memberService.findById(id);

        if (member.isPresent()) {
            return new ResponseEntity<>(member.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<List<Member>> findAllMembers() {
        List<Member> members = memberService.findAll();
        if (members.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    @GetMapping("/findByFirstName/{firstname}")
    public ResponseEntity<List<Member>> findByFirstName(@PathVariable("firstname") String firstname) {
        List<Member> members = memberService.findByFirstName(firstname);
        if (members.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    @GetMapping("/findByActive")
    public ResponseEntity<List<Member>> findByActiveMembers() {
        List<Member> members = memberService.findByActiveMembers();
        if (members.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    @GetMapping("/findByInactive")
    public ResponseEntity<List<Member>> findByInctiveMembers() {
        List<Member> members = memberService.findByInactiveMembers();
        if (members.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    @GetMapping("/findByEmail/{email}")
    public ResponseEntity<Optional<Member>> findByEmail(@PathVariable("email") String email) {
        Optional<Member> member = memberService.findByEmail(email);
        if (member.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else if (member.isPresent())
            return new ResponseEntity<>(member, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/save")
    public ResponseEntity<Member> save(@RequestBody Member input) {
        //ensure that the email entered is unique
        Optional<Member> member = memberService.findByEmail(input.getEmail());
        if (member.isPresent()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        if (input.getEmail() == null
                || input.getFirstname() == null
                || input.getLastname() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (input.getEmail().isEmpty()
                || input.getFirstname().isEmpty()
                || input.getLastname().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Member savedMember = memberService.save(input);
        return new ResponseEntity<>(savedMember, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Member> updateMember(@PathVariable("id") Integer id, @RequestBody Member input) {
        Optional<Member> member = memberService.findById(id);

        if (member.isPresent()) {
            if (input.getEmail() == null
                    || input.getFirstname() == null
                    || input.getLastname() == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            if (input.getEmail().isEmpty()
                    || input.getFirstname().isEmpty()
                    || input.getLastname().isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            Member updatedMember = memberService.updateMember(id, input);
            return new ResponseEntity<>(updatedMember, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/activate/{id}")
    public ResponseEntity<Member> activateMember(@PathVariable("id") Integer id) {
        Optional<Member> member = memberService.findById(id);

        if (member.isPresent()) {
            Member activatedMember = memberService.activateMember(id);
            return new ResponseEntity<>(activatedMember, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/deactivate/{id}")
    public ResponseEntity<Member> deactivateMember(@PathVariable("id") Integer id) {
        Optional<Member> member = memberService.findById(id);

        if (member.isPresent()) {
            Member deactivatedMember = memberService.deactivateMember(id);
            return new ResponseEntity<>(deactivatedMember, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        Optional<Member> optionalMember = memberService.findById(id);

        if (optionalMember.isPresent()) {
            Member member = optionalMember.get();

            Optional<MemberSubscription> optionalMemberSubscription = memberSubscriptionService.findMemberSubscription(member);

            if (optionalMemberSubscription.isPresent()) {
                memberSubscriptionService.deleteMemberSubscription(optionalMemberSubscription.get().getId());
            }

            memberService.deleteById(id);

            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @GetMapping("/page/{offset}")
    public ResponseEntity<Page<Member>> findMembersWithPagination(@PathVariable int offset) {
        Page<Member> members = memberService.findMembersWithPagination(offset);
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    @GetMapping("/get-All-member-subscriptions")
    public ResponseEntity<List<MemberSubscription>> findAll() {
        List<MemberSubscription> memberSubscriptions = memberSubscriptionService.findAll();
        if (memberSubscriptions.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(memberSubscriptions, HttpStatus.OK);
    }

    @GetMapping("get-member-subscription/{id}")
    public ResponseEntity<MemberSubscription> findMemberSubscription(@PathVariable Integer id) {
        Optional<Member> optionalMember = memberService.findById(id);

        if (optionalMember.isPresent()) {
            Member member = optionalMember.get();

            Optional<MemberSubscription> optionalMemberSubscription = memberSubscriptionService.findMemberSubscription(member);

            if (optionalMemberSubscription.isPresent()) {
                MemberSubscription memberSubscription = optionalMemberSubscription.get();

                return new ResponseEntity<>(memberSubscription, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/subscribe")
    public ResponseEntity<MemberSubscription> subscribe(@RequestParam Integer id, @RequestParam String subscriptionName) {

        if (!Objects.equals(subscriptionName, "GOLD") && !Objects.equals(subscriptionName, "SILVER") && !Objects.equals(subscriptionName, "BRONZE")) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Optional<Member> memberById = memberService.findById(id);

        if (memberById.isPresent()) {
            Optional<MemberSubscription> memberSubscription = memberSubscriptionService.findMemberSubscription(memberById.get());
            if (memberSubscription.isPresent()) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
            MemberSubscription subscribe = memberSubscriptionService.subscribe(id, subscriptionName);
            return new ResponseEntity<>(subscribe, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete-member-subscription/{id}")
    public ResponseEntity<?> deleteMemberSubscription(@PathVariable("id") Integer id) {
        Optional<Member> optionalMember = memberService.findById(id);

        if (optionalMember.isPresent()) {
            Member member = optionalMember.get();

            Optional<MemberSubscription> optionalMemberSubscription = memberSubscriptionService.findMemberSubscription(member);

            if (optionalMemberSubscription.isPresent()) {
                memberSubscriptionService.deleteMemberSubscription(optionalMemberSubscription.get().getId());
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
