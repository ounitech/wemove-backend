package com.ounitech.wemove.controllers;


import com.ounitech.wemove.services.MemberService;
import com.ounitech.wemove.models.Member;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    public ResponseEntity<Member> save(
            @RequestBody Member member
    ) {
        if (member.getEmail() == null || member.getFirstname() == null || member.getLastname() == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        if (member.getEmail().isEmpty() || member.getFirstname().isEmpty() || member.getLastname().isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(memberService.save(member), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
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

    @PutMapping("/{id}")
    public ResponseEntity<Member> updateUser(
            @PathVariable("id") Integer id,
            @RequestBody Member member
    ) {
        Optional<Member> _user = memberService.findById(id);

        if (_user.isPresent()) {
            if (member.getEmail() == null || member.getFirstname() == null || member.getLastname() == null)
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            if (member.getEmail().isEmpty() || member.getFirstname().isEmpty() || member.getLastname().isEmpty())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            Member updatedMember = memberService.updateMember(id, member);
            return new ResponseEntity<>(updatedMember, HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(
            @PathVariable("id") Integer id
    ) {
        Optional<Member> member = memberService.findById(id);

        if (member.isPresent()) {
            memberService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

}
