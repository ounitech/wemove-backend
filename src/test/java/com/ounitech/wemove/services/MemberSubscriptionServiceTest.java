package com.ounitech.wemove.services;

import com.ounitech.wemove.models.Member;
import com.ounitech.wemove.models.MemberSubscription;
import com.ounitech.wemove.models.Subscription;
import com.ounitech.wemove.repositories.MemberRepository;
import com.ounitech.wemove.repositories.MemberSubscriptionRepository;
import com.ounitech.wemove.repositories.SubscriptionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@Service
class MemberSubscriptionServiceTest {

    @Mock
    private MemberSubscriptionRepository memberSubscriptionRepository;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private SubscriptionRepository subscriptionRepository;

    @InjectMocks
    private MemberSubscriptionService memberSubscriptionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findMemberSubscriptionTest_nominal_case() throws Exception {
        //Given
        Member member = new Member();
        member.setId(999);

        MemberSubscription memberSubscription = new MemberSubscription();
        memberSubscription.setStartDate(LocalDate.now());
        memberSubscription.setSubscription(new Subscription());
        memberSubscription.setId(1000);
        memberSubscription.setMember(member);

        //When
        Mockito.when(memberSubscriptionRepository.findBymember(member)).thenReturn(Optional.of(memberSubscription));

        Optional<MemberSubscription> response = memberSubscriptionService.findMemberSubscription(member);

        //Then
        assertThat(response).isNotNull();
        assertThat(response.get().getId()).isEqualTo(1000);
        assertThat(response.get().getMember().getId()).isEqualTo(999);


        Mockito.verify(memberSubscriptionRepository, Mockito.times(1)).findBymember(member);
    }

    @Test
    void subscribeTest() throws Exception {
        //Given
        Integer id = 1000;
        String subscriptionName = "GOLD";

        Subscription subscription = new Subscription();
        subscription.setName(subscriptionName);
        subscription.setPrice(100);

        Member member = new Member();
        member.setId(id);
        member.setActive(true);

        MemberSubscription memberSubscription = new MemberSubscription();
        memberSubscription.setSubscription(subscription);
        memberSubscription.setMember(member);
        memberSubscription.setPaid(subscription.getPrice());
        memberSubscription.setStartDate(LocalDate.now());

        //When
        Mockito.when(memberRepository.findById(id)).thenReturn(Optional.of(member));
        Mockito.when(subscriptionRepository.findByName(subscriptionName)).thenReturn(Optional.of(subscription));
        Mockito.when(memberSubscriptionRepository.save(Mockito.any(MemberSubscription.class))).thenReturn(memberSubscription);

        MemberSubscription response = memberSubscriptionService.subscribe(id, subscriptionName);

        //Then
        assertThat(response).isNotNull();
        assertThat(response.getMember()).isEqualTo(member);
        assertThat(response.getSubscription()).isEqualTo(subscription);
        assertThat(response.getMember().getActive()).isEqualTo(true);

    }


}