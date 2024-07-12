package com.ounitech.wemove.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name = "subscription")
public class Subscription {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "name", nullable = false)
    private String subscriptionName;

    @Column(name = "price", nullable = false)
    private int subscriptionPrice;

    public enum Duration {
        DAILY,
        MONTHLY,
        YEAR
    }

    @Column(name = "duration", nullable = false)
    @Enumerated(EnumType.STRING)
    private Duration duration;
    @Column(name = "active", nullable = false)
    private Byte active;

    @OneToMany(mappedBy = "subscription")
    private Set<MemberSubscription> memberSubscriptions;

}
