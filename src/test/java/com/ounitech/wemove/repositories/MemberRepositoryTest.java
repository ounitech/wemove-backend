package com.ounitech.wemove.repositories;

import com.ounitech.wemove.models.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    void setUp() {
        Member member1 = new Member();
        member1.setFirstname("stephane");
        member1.setLastname("nicoll");
        member1.setEmail("stephanenicoll@gmail.com");

        Member member2 = new Member();
        member2.setFirstname("sebastien");
        member2.setLastname("deleuze");
        member2.setEmail("sebastiendeleuze@gmail.com");


        memberRepository.save(member1);
        memberRepository.save(member2);
    }

    @Test
    void findById() {
        // When
        Optional<Member> member = memberRepository.findById(4);

        // Then
        assertThat(member).isPresent();
        assertThat(member.get().getId()).isEqualTo(4);
        assertThat(member.get().getFirstname()).isEqualTo("stephane");
        assertThat(member.get().getLastname()).isEqualTo("nicoll");
    }

}