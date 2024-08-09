package com.ounitech.wemove.repositories;

import com.ounitech.wemove.models.Member;
import com.ounitech.wemove.models.MemberSubscription;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.properties")
class MemberSubscriptionRepositoryTest {

    @Autowired
    private MemberSubscriptionRepository memberSubscriptionRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        jdbcTemplate.execute("""
                    INSERT INTO member(id, firstname, lastname, email, active)
                    VALUES (100, 'firstname', 'lastname', 'email', 1);
                """);

        jdbcTemplate.execute("""
                    INSERT INTO subscription(id, name, price, active, duration)
                    VALUES (1, 'GOLD', 300, true, 'year');
                """);

        jdbcTemplate.execute("""
                    INSERT INTO member_subscription(id, startdate, paid, subscriptionid,memberid)
                    VALUES (1, now(), 300, 1,100);
                """);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findBymember() {
        //Given
        Member member = new Member();
        member.setId(100);
        member.setEmail("email");
        member.setLastname("lastname");
        member.setFirstname("firstname");

        Optional<MemberSubscription> sub = memberSubscriptionRepository.findBymember(member);

        assertThat(sub.isPresent()).isTrue();
        assertThat(sub.get().getId()).isEqualTo(1);
        assertThat(sub.get().getMember().getId()).isEqualTo(100);

    }

    @Test
    void countGoldMembers() {
        long countGoldMembers = memberSubscriptionRepository.countGoldMembers();

        assertThat(countGoldMembers).isEqualTo(1);
    }

    @Test
    void countSilverMembers() {
        long countSilverMembers = memberSubscriptionRepository.countSilverMembers();

        assertThat(countSilverMembers).isEqualTo(0);
    }

    @Test
    void countBronzeMembers() {
        long countBronzeMembers = memberSubscriptionRepository.countBronzeMembers();

        assertThat(countBronzeMembers).isEqualTo(0);
    }

}