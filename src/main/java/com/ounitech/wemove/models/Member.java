package com.ounitech.wemove.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name = "member")
public class Member {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(name = "firstname", nullable = false)
    private String firstname;
    @Column(name = "lastname", nullable = false)
    private String lastname;
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Column(name = "gender")
    private String gender;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "picture")
    private String picture;

    @Column(name = "active", nullable = false, columnDefinition = "BYTE DEFAULT 0")
    private Byte active;

//    @OneToOne
//    @JoinColumn(name = "memberSubid", referencedColumnName = "id")
//    private MemberSubscription memberSubscription;

    @OneToMany(mappedBy = "member")
    private Set<Entry> entries;

}