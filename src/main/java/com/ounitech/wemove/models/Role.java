package com.ounitech.wemove.models;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue
    private Integer id;

//    enum Roles {
//        Manager, ViceManager, Maintenance, Trainer
//    }

    @Column(name = "name")
    private String roleName;

    @OneToMany(mappedBy = "role")
    private Set<Staff> staffs;
}
