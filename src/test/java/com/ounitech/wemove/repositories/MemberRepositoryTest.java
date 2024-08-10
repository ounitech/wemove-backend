package com.ounitech.wemove.repositories;

import com.ounitech.wemove.models.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {

        jdbcTemplate.execute("""
                    INSERT INTO member(id, firstname, lastname, email,gender, active)
                    VALUES (1, 'stephane', 'nicoll', 'stephanenicoll@gmail.com','Male', false);
                """);
        jdbcTemplate.execute("""
                    INSERT INTO member(id, firstname, lastname, email,gender, active)
                    VALUES (2, 'Mohamed', 'ali', 'Mohamedali@gmail.com','Male', false);
                """);
        jdbcTemplate.execute("""
                    INSERT INTO member(id, firstname, lastname, email,gender, active)
                    VALUES (3, 'Mohamed', 'amine', 'Mohamedamine@gmail.com','Male', true);
                """);

        jdbcTemplate.execute("""
                    INSERT INTO member(id, firstname, lastname, email,gender, active)
                    VALUES (4, 'ines', 'ines', 'ines@gmail.com','Female', true);
                """);
    }

    @Test
    void findById() {
        // When
        //List<Member> members = memberRepository.findAll();
        Optional<Member> member = memberRepository.findById(1);

        // Then
        assertThat(member).isPresent();
        assertThat(member.get().getId()).isEqualTo(1);
        assertThat(member.get().getFirstname()).isEqualTo("stephane");
        assertThat(member.get().getLastname()).isEqualTo("nicoll");
    }

    @Test
    void findbyFirstnameTest() {
        //GIVEN
        Member member1 = new Member();
        member1.setFirstname("stephane");
        member1.setLastname("nicoll");
        member1.setEmail("stephanenicoll@gmail.com");
        member1.setActive(false);

        Member member2 = new Member();
        member2.setFirstname("Mohamed");
        member2.setLastname("ali");
        member2.setEmail("Mohamedali@gmail.com");
        member2.setActive(false);

        Member member3 = new Member();
        member3.setFirstname("Mohamed");
        member3.setLastname("amine");
        member3.setEmail("Mohamedamine@gmail.com");

        //WHEN
        List<Member> members = memberRepository.findByFirstname("Mohamed");


        //THEN
        assertThat(members.size()).isEqualTo(2);
        assertThat(members.contains(member1)).isFalse();
        assertThat(members.contains(member2)).isTrue();
        assertThat(members.contains(member3)).isTrue();
    }

    @Test
    void findByActiveTest() {
        List<Member> activeMembers = memberRepository.findByActive(true);

        assertThat(activeMembers.size()).isEqualTo(2);
        assertThat(activeMembers.get(0).getEmail()).isEqualTo("Mohamedamine@gmail.com");

    }

    @Test
    void findByInactiveTest() {
        Member member1 = new Member();
        member1.setFirstname("stephane");
        member1.setLastname("nicoll");
        member1.setEmail("stephanenicoll@gmail.com");
        member1.setActive(false);

        Member member2 = new Member();
        member2.setFirstname("Mohamed");
        member2.setLastname("ali");
        member2.setEmail("Mohamedali@gmail.com");
        member2.setActive(false);


        List<Member> activeMembers = memberRepository.findByActive(false);

        assertThat(activeMembers.size()).isEqualTo(2);
        assertThat(activeMembers.contains(member1)).isTrue();
        assertThat(activeMembers.contains(member2)).isTrue();

    }

    @Test
    void findByEmailTest() {
        Member member = memberRepository.findByEmail("stephanenicoll@gmail.com");

        assertThat(member).isNotNull();
        assertThat(member.getFirstname()).isEqualTo("stephane");
        assertThat(member.getLastname()).isEqualTo("nicoll");
        assertThat(member.getEmail()).isEqualTo("stephanenicoll@gmail.com");

    }

    @Test
    void countByGenderCountTest() {
        long countMale = memberRepository.countByGender("Male");
        long countFemale = memberRepository.countByGender("Female");

        assertThat(countMale).isEqualTo(3);
        assertThat(countFemale).isEqualTo(1);
    }

    @Test
    void countActiveMembersTest() {
        long countActive = memberRepository.countActiveMembers();

        assertThat(countActive).isEqualTo(2);
    }

    @Test
    void countInactiveMembersTest() {
        long countInactive = memberRepository.countInactiveMembers();

        assertThat(countInactive).isEqualTo(2);
    }


}