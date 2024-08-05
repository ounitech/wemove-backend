package com.ounitech.wemove.controllers;


import com.ounitech.wemove.models.MemberSubscription;
import com.ounitech.wemove.services.MemberService;
import com.ounitech.wemove.models.Member;
import com.ounitech.wemove.services.MemberSubscriptionService;
import org.springframework.http.HttpStatus;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;
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
    public ResponseEntity<Member> findById(
            @PathVariable("id") Integer id
    ) {
        Optional<Member> member = memberService.findById(id);

        if (member.isPresent()) {
            return new ResponseEntity<>(member.get(), HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<List<Member>> findAllMembers() {
        if (memberService.findAll().isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(memberService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/findByFirstName/{firstname}")
    public ResponseEntity<List<Member>> findByFirstName(
            @PathVariable("firstname") String firstname
    ) {
        if (memberService.findByFirstName(firstname).isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(memberService.findByFirstName(firstname), HttpStatus.OK);
    }

    @GetMapping("/findByActive")
    public ResponseEntity<List<Member>> findByActiveMembers() {
        if (memberService.findByActiveMembers().isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(memberService.findByActiveMembers(), HttpStatus.OK);
    }

    @GetMapping("/findByInactive")
    public ResponseEntity<List<Member>> findByInctiveMembers() {
        if (memberService.findByInactiveMembers().isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(memberService.findByInactiveMembers(), HttpStatus.OK);
    }

    @GetMapping("/findByEmail/{email}")
    public ResponseEntity<Member> findByEmail(
            @PathVariable("email") String email
    ) {
        if (memberService.findByEmail(email) == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(memberService.findByEmail(email), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Member> save(
            @RequestBody Member member
    ) {
        //ensure that the email entered is unique
        if (memberService.findByEmail(member.getEmail()) != null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        if (member.getEmail() == null || member.getFirstname() == null || member.getLastname() == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        if (member.getEmail().isEmpty() || member.getFirstname().isEmpty() || member.getLastname().isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(memberService.save(member), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Member> updateMember(
            @PathVariable("id") Integer id,
            @RequestBody Member member
    ) {
        Optional<Member> member1 = memberService.findById(id);

        if (member1.isPresent()) {
            if (member.getEmail() == null || member.getFirstname() == null || member.getLastname() == null)
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            if (member.getEmail().isEmpty() || member.getFirstname().isEmpty() || member.getLastname().isEmpty())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            Member updatedMember = memberService.updateMember(id, member);
            return new ResponseEntity<>(updatedMember, HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/activate/{id}")
    public ResponseEntity<Member> activateMember(
            @PathVariable("id") Integer id
    ) {
        Optional<Member> member = memberService.findById(id);

        if (member.isPresent()) {
            Member activatedMember = memberService.activateMember(id);
            return new ResponseEntity<>(activatedMember, HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/deactivate/{id}")
    public ResponseEntity<Member> deactivateMember(
            @PathVariable("id") Integer id
    ) {
        Optional<Member> member = memberService.findById(id);

        if (member.isPresent()) {
            Member deactivatedMember = memberService.deactivateMember(id);
            return new ResponseEntity<>(deactivatedMember, HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(
            @PathVariable("id") Integer id
    ) {
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
    public ResponseEntity<Page<Member>> findMembersWithPagination(
            @PathVariable int offset
    ) {
        return new ResponseEntity<>(memberService.findMembersWithPagination(offset), HttpStatus.OK);
    }
}
