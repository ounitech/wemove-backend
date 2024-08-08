package com.ounitech.wemove.services;

import com.ounitech.wemove.models.Member;
import com.ounitech.wemove.repositories.MemberRepository;
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
    void should_successfully_save_a_member() {
        //Given
        Member member = new Member();
        member.setFirstname("mohamed");
        member.setLastname("ali");
        member.setEmail("med@gmail.com");

        Member savedMember = new Member();
        savedMember.setFirstname("mohamed");
        savedMember.setLastname("ali");
        savedMember.setEmail("med@gmail.com");

        //When
        Mockito.when(memberRepository.save(member)).thenReturn(savedMember);
        Member response = memberService.save(member);

        //Then
        assertThat(response).isNotNull();
        assertThat(response.getFirstname()).isEqualTo(member.getFirstname());
        assertThat(response.getEmail()).isEqualTo(member.getEmail());
        assertThat(response.getLastname()).isEqualTo(member.getLastname());

        Mockito.verify(memberRepository, Mockito.times(1)).save(member);
    }

    @Test
    void findById_nominal_case() {
        // Given
        Member member = new Member();
        member.setFirstname("jorgen");
        member.setLastname("hoeller");
        member.setId(1000);

        // When
        Mockito.when(memberRepository.findById(1000)).thenReturn(Optional.of(member));
        Optional<Member> response = memberService.findById(1000);

        // Then
        assertThat(response.isPresent()).isTrue();
        assertThat(response.get().getFirstname()).isEqualTo("jorgen");
        assertThat(response.get().getLastname()).isEqualTo("hoeller");

        Mockito.verify(memberRepository, Mockito.times(1)).findById(1000);
    }

    @Test
    void findById_notFound() {
        //Given
        Mockito.when(memberRepository.findById(12334))
                .thenReturn(Optional.empty());

        // When
        Optional<Member> response = memberService.findById(12334);

        // Then
        assertThat(response.isPresent()).isFalse();
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

        Mockito.verify(memberRepository, Mockito.times(1)).findAll();
    }

    @Test
    void should_successfully_update_a_member() {
        // Given
        Member member = new Member();
        member.setFirstname("jorgen");
        member.setLastname("hoeller");
        member.setEmail("jorgen@gmail.com");
        member.setId(1000);

        Member newMember = new Member();
        newMember.setFirstname("cristiano");
        newMember.setLastname("ronaldo");
        newMember.setEmail("cristiano@gmail.com");

        Member updatedMember = new Member();
        updatedMember.setFirstname("cristiano");
        updatedMember.setLastname("ronaldo");
        updatedMember.setEmail("cristiano@gmail.com");
        updatedMember.setId(1000);

        //When
        Mockito.when(memberRepository.findById(1000)).thenReturn(Optional.of(member));
        Mockito.when(memberRepository.save(member)).thenReturn(updatedMember);

        Member response = memberService.updateMember(1000, newMember);


        //Then
        assertThat(response).isNotNull();
        assertThat(response.getFirstname()).isEqualTo(updatedMember.getFirstname());
        assertThat(response.getLastname()).isEqualTo(updatedMember.getLastname());
        assertThat(response.getEmail()).isEqualTo(updatedMember.getEmail());


        Mockito.verify(memberRepository, Mockito.times(1)).findById(1000);
        Mockito.verify(memberRepository, Mockito.times(1)).save(member);

    }

    @Test
    void should_successfully_delete_a_member() {
        // Given
        Member member = new Member();
        member.setFirstname("jorgen");
        member.setLastname("hoeller");
        member.setEmail("jorgen@gmail.com");
        member.setId(1000);

        //When
        Mockito.doNothing().when(memberRepository).deleteById(1000);
        memberService.deleteById(1000);

        Mockito.verify(memberRepository, Mockito.times(1)).deleteById(1000);
    }

    @Test
    void should_successfully_activate_a_member() {
        // Given
        Member member = new Member();
        member.setFirstname("jorgen");
        member.setLastname("hoeller");
        member.setEmail("jorgen@gmail.com");
        member.setActive(false);
        member.setId(1000);

        Member activatedMember = new Member();
        activatedMember.setFirstname("jorgen");
        activatedMember.setLastname("hoeller");
        activatedMember.setEmail("jorgen@gmail.com");
        activatedMember.setActive(true);
        activatedMember.setId(1000);

        //When
        Mockito.when(memberRepository.findById(1000)).thenReturn(Optional.of(member));
        Mockito.when(memberRepository.save(activatedMember)).thenReturn(activatedMember);

        Member response = memberService.activateMember(1000);

        //Then
        assertThat(response).isNotNull();
        assertThat(response.getActive()).isEqualTo(true);
        assertThat(response.getEmail()).isEqualTo(member.getEmail());

        Mockito.verify(memberRepository, Mockito.times(1)).findById(1000);
        Mockito.verify(memberRepository, Mockito.times(1)).save(member);

    }

}