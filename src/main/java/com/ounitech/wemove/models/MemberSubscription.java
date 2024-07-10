package com.ounitech.wemove.models;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "member_subscription")
public class MemberSubscription {

    @Id
    @GeneratedValue
    private Integer id;



    @Column(name = "startdate", nullable = false)
    private LocalDate startDate;

    @Column(name = "paid", nullable = false)
    private int paid;
    @ManyToOne
    @JoinColumn(name = "subscriptionid", referencedColumnName = "id", nullable = false)
    private Subscription subscription;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "memberid", referencedColumnName = "id", nullable = false)
    private Member member;
}
