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

    private Integer memberId;

    private Integer subscriptionId;

    @Column(name = "startdate", nullable = false)
    private LocalDate startDate;

    @Column(name = "paid", nullable = false)
    private String paid;
}
