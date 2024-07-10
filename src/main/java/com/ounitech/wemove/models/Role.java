package com.ounitech.wemove.models;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue
    private Integer id;

    enum Roles {
        Manager, ViceManager, Maintenance, Trainer
    }

    @Column(name = "roleName")
    private Roles roleName;
}
