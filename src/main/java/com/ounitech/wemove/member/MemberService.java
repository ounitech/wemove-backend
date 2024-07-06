package com.ounitech.wemove.member;

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
}
