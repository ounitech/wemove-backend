package com.ounitech.wemove.models;


import jakarta.persistence.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.time.LocalDateTime;

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

    public Entry() {
    }

    public Integer getId() {
        return this.id;
    }

    public LocalDateTime getEntryTime() {
        return this.entryTime;
    }

    public LocalDateTime getLeaveTime() {
        return this.leaveTime;
    }

    public Member getMember() {
        return this.member;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setEntryTime(LocalDateTime entryTime) {
        this.entryTime = entryTime;
    }

    public void setLeaveTime(LocalDateTime leaveTime) {
        this.leaveTime = leaveTime;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Entry entry)) {
            return false;
        }

        return new EqualsBuilder()
                .append(entryTime, entry.entryTime)
                .append(member, entry.member)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(entryTime)
                .append(member)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("entryTime", entryTime)
                .append("leaveTime", leaveTime)
                .append("member", member.getEmail())
                .toString();
    }
}
