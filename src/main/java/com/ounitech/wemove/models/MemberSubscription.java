package com.ounitech.wemove.models;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "member_subscription")
public class MemberSubscription {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "startdate", nullable = false)
    private LocalDate startDate;

    @Column(name = "paid", nullable = false)
    private int paid;

    @ManyToOne
    @JoinColumn(name = "subscriptionid", referencedColumnName = "id", nullable = false)
    private Subscription subscription;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "memberid", referencedColumnName = "id", nullable = false)
    private Member member;

    public MemberSubscription() {
    }

    public Integer getId() {
        return this.id;
    }

    public LocalDate getStartDate() {
        return this.startDate;
    }

    public int getPaid() {
        return this.paid;
    }

    public Subscription getSubscription() {
        return this.subscription;
    }

    public Member getMember() {
        return this.member;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setPaid(int paid) {
        this.paid = paid;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof MemberSubscription)) return false;
        final MemberSubscription other = (MemberSubscription) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$startDate = this.getStartDate();
        final Object other$startDate = other.getStartDate();
        if (this$startDate == null ? other$startDate != null : !this$startDate.equals(other$startDate)) return false;
        if (this.getPaid() != other.getPaid()) return false;
        final Object this$subscription = this.getSubscription();
        final Object other$subscription = other.getSubscription();
        if (this$subscription == null ? other$subscription != null : !this$subscription.equals(other$subscription))
            return false;
        final Object this$member = this.getMember();
        final Object other$member = other.getMember();
        if (this$member == null ? other$member != null : !this$member.equals(other$member)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof MemberSubscription;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $startDate = this.getStartDate();
        result = result * PRIME + ($startDate == null ? 43 : $startDate.hashCode());
        result = result * PRIME + this.getPaid();
        final Object $subscription = this.getSubscription();
        result = result * PRIME + ($subscription == null ? 43 : $subscription.hashCode());
        final Object $member = this.getMember();
        result = result * PRIME + ($member == null ? 43 : $member.hashCode());
        return result;
    }

    public String toString() {
        return "MemberSubscription(id=" + this.getId() + ", startDate=" + this.getStartDate() + ", paid=" + this.getPaid() + ", subscription=" + this.getSubscription() + ", member=" + this.getMember() + ")";
    }
}
