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
    private Integer memberId;

    @Column(name = "entryTime", nullable = false)
    private LocalDateTime entryTime;

    @Column(name = "leaveTime", nullable = false)
    private LocalDateTime leaveTime;


}
