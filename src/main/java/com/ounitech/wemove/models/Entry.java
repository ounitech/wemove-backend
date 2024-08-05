package com.ounitech.wemove.models;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "entry")
public class Entry {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "entrytime", nullable = false)
    private LocalDateTime entryTime;

    @Column(name = "leavetime", nullable = false)
    private LocalDateTime leaveTime;

    @ManyToOne
    @JoinColumn(name = "memberid", referencedColumnName = "id", nullable = false)
    private Member member;
}
