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

    public Member updateMember(Integer id, Member updatedMember) {
        Optional<Member> memberById = memberRepository.findById(id);

        Member member = memberById.get();
        member.setFirstname(updatedMember.getFirstname());
        member.setLastname(updatedMember.getLastname());
        member.setEmail(updatedMember.getEmail());
        return memberRepository.save(member);
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
        member.setActive(true);

        return memberRepository.save(member);
    }

    public Member deactivateMember(Integer id) {
        Optional<Member> memberById = memberRepository.findById(id);

        Member member = memberById.get();
        member.setActive(false);

        return memberRepository.save(member);
    }

    public List<Member> findByFirstName(String firstname) {
        return memberRepository.findByFirstname(firstname);
    }

    public List<Member> findByActiveMembers() {
        return memberRepository.findByActive(true);
    }

    public List<Member> findByInactiveMembers() {
        return memberRepository.findByActive(false);
    }

    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email);
    }

    public Page<Member> findMembersWithPagination(int offset) {
        return memberRepository.findAll(PageRequest.of(offset, 10));
    }
}
