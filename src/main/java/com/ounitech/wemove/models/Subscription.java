package com.ounitech.wemove.models;

import jakarta.persistence.*;
import lombok.Data;

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

    @Column(name = "active", nullable = false)
    private Byte active;

}
