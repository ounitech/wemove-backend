package com.ounitech.wemove.services;

import com.ounitech.wemove.models.Member;
import com.ounitech.wemove.repositories.MemberRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member save(Member member) {
        return memberRepository.save(member);
    }

    public Optional<Member> findById(Integer id) {
        return memberRepository.findById(id);
    }

    public Member updateMember(Integer id, Member member) {
        Optional<Member> memberById = memberRepository.findById(id);

        Member member1 = memberById.get();
        member1.setFirstname(member.getFirstname());
        member1.setLastname(member.getLastname());
        member1.setEmail(member.getEmail());
        return memberRepository.save(member1);
    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    public void deleteById(Integer id) {
        memberRepository.deleteById(id);
    }

    public Member activateMember(Integer id) {
        Optional<Member> memberById = memberRepository.findById(id);

        Member member = memberById.get();
        member.setActive((byte) 1);

        return memberRepository.save(member);
    }

    public Member deactivateMember(Integer id) {
        Optional<Member> memberById = memberRepository.findById(id);

        Member member = memberById.get();
        member.setActive((byte) 0);

        return memberRepository.save(member);
    }

    public List<Member> findByFirstName(String firstname) {
        return memberRepository.findByfirstname(firstname);
    }

    public List<Member> findByActiveMembers() {
        return memberRepository.findByactive((byte) 1);
    }

    public List<Member> findByInactiveMembers() {
        return memberRepository.findByactive((byte) 0);
    }

    public Member findByEmail(String email) {
        return memberRepository.findByemail(email);
    }

    public Page<Member> findMembersWithPagination(int offset) {
        Page<Member> members = memberRepository.findAll(PageRequest.of(offset, 10));
        return members;
    }


}
