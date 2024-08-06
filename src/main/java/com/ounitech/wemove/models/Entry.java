package com.ounitech.wemove.models;


import jakarta.persistence.*;

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

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Entry)) return false;
        final Entry other = (Entry) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$entryTime = this.getEntryTime();
        final Object other$entryTime = other.getEntryTime();
        if (this$entryTime == null ? other$entryTime != null : !this$entryTime.equals(other$entryTime)) return false;
        final Object this$leaveTime = this.getLeaveTime();
        final Object other$leaveTime = other.getLeaveTime();
        if (this$leaveTime == null ? other$leaveTime != null : !this$leaveTime.equals(other$leaveTime)) return false;
        final Object this$member = this.getMember();
        final Object other$member = other.getMember();
        if (this$member == null ? other$member != null : !this$member.equals(other$member)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Entry;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $entryTime = this.getEntryTime();
        result = result * PRIME + ($entryTime == null ? 43 : $entryTime.hashCode());
        final Object $leaveTime = this.getLeaveTime();
        result = result * PRIME + ($leaveTime == null ? 43 : $leaveTime.hashCode());
        final Object $member = this.getMember();
        result = result * PRIME + ($member == null ? 43 : $member.hashCode());
        return result;
    }

    public String toString() {
        return "Entry(id=" + this.getId() + ", entryTime=" + this.getEntryTime() + ", leaveTime=" + this.getLeaveTime() + ", member=" + this.getMember() + ")";
    }
}
