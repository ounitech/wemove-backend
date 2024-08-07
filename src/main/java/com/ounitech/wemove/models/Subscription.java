package com.ounitech.wemove.models;

import jakarta.persistence.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Set;

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

    public enum Duration {
        daily,
        monthly,
        year
    }

    @Column(name = "duration", nullable = false)
    @Enumerated(EnumType.STRING)
    private Duration duration;

    @Column(name = "active", nullable = false)
    private Byte active;

    @OneToMany(mappedBy = "subscription")
    private Set<MemberSubscription> memberSubscriptions;

    public Subscription() {
    }

    public Integer getId() {
        return this.id;
    }

    public String getSubscriptionName() {
        return this.subscriptionName;
    }

    public int getSubscriptionPrice() {
        return this.subscriptionPrice;
    }

    public Duration getDuration() {
        return this.duration;
    }

    public Byte getActive() {
        return this.active;
    }

    public Set<MemberSubscription> getMemberSubscriptions() {
        return this.memberSubscriptions;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setSubscriptionName(String subscriptionName) {
        this.subscriptionName = subscriptionName;
    }

    public void setSubscriptionPrice(int subscriptionPrice) {
        this.subscriptionPrice = subscriptionPrice;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public void setActive(Byte active) {
        this.active = active;
    }

    public void setMemberSubscriptions(Set<MemberSubscription> memberSubscriptions) {
        this.memberSubscriptions = memberSubscriptions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Subscription that)) {
            return false;
        }

        return new EqualsBuilder()
                .append(subscriptionName, that.subscriptionName)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(subscriptionName)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("subscriptionName", subscriptionName)
                .append("subscriptionPrice", subscriptionPrice)
                .append("duration", duration)
                .append("active", active)
                .toString();
    }
}
