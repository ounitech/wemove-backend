package com.ounitech.wemove.member;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class MemberServiceTest {

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private MemberService memberService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findById_nominal_case() {
        // Given
        Member member = new Member();
        member.setFirstname("jorgen");
        member.setLastname("hoeller");

        // When
        Mockito.when(memberRepository.findById(11))
                .thenReturn(Optional.of(member));
        Optional<Member> result = memberService.findById(11);

        // Then
        assertThat(result.isPresent()).isTrue();
        assertThat(result.get().getFirstname()).isEqualTo("jorgen");
        assertThat(result.get().getLastname()).isEqualTo("hoeller");
    }

    @Test
    void findById_notFound() {
        //Given
        Mockito.when(memberRepository.findById(11))
                .thenReturn(Optional.empty());

        // When
        Optional<Member> result = memberService.findById(11);

        // Then
        assertThat(result.isPresent()).isFalse();
    }

    @Test
    void findAllUsers() {
        // Given
        Member member = new Member();
        member.setFirstname("jorgen");
        member.setLastname("hoeller");

        Member member2 = new Member();
        member2.setFirstname("cristiano");
        member2.setLastname("ronaldo");

        //When
        Mockito.when(memberRepository.findAll()).thenReturn(List.of(member, member2));
        List<Member> result = memberService.findAll();

        //Then
        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(2);
    }

}