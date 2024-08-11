package com.ounitech.wemove.models;


import jakarta.persistence.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof MemberSubscription that)) {
            return false;
        }

        return new EqualsBuilder()
                .append(id, that.id)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("startDate", startDate)
                .append("paid", paid)
                .append("subscription", subscription.getName())
                .append("member", member.getEmail())
                .toString();
    }
}
