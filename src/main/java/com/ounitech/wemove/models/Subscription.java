package com.ounitech.wemove.models;

import jakarta.persistence.*;

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

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Subscription)) return false;
        final Subscription other = (Subscription) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$subscriptionName = this.getSubscriptionName();
        final Object other$subscriptionName = other.getSubscriptionName();
        if (this$subscriptionName == null ? other$subscriptionName != null : !this$subscriptionName.equals(other$subscriptionName))
            return false;
        if (this.getSubscriptionPrice() != other.getSubscriptionPrice()) return false;
        final Object this$duration = this.getDuration();
        final Object other$duration = other.getDuration();
        if (this$duration == null ? other$duration != null : !this$duration.equals(other$duration)) return false;
        final Object this$active = this.getActive();
        final Object other$active = other.getActive();
        if (this$active == null ? other$active != null : !this$active.equals(other$active)) return false;
        final Object this$memberSubscriptions = this.getMemberSubscriptions();
        final Object other$memberSubscriptions = other.getMemberSubscriptions();
        if (this$memberSubscriptions == null ? other$memberSubscriptions != null : !this$memberSubscriptions.equals(other$memberSubscriptions))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Subscription;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $subscriptionName = this.getSubscriptionName();
        result = result * PRIME + ($subscriptionName == null ? 43 : $subscriptionName.hashCode());
        result = result * PRIME + this.getSubscriptionPrice();
        final Object $duration = this.getDuration();
        result = result * PRIME + ($duration == null ? 43 : $duration.hashCode());
        final Object $active = this.getActive();
        result = result * PRIME + ($active == null ? 43 : $active.hashCode());
        final Object $memberSubscriptions = this.getMemberSubscriptions();
        result = result * PRIME + ($memberSubscriptions == null ? 43 : $memberSubscriptions.hashCode());
        return result;
    }

    public String toString() {
        return "Subscription(id=" + this.getId() + ", subscriptionName=" + this.getSubscriptionName() + ", subscriptionPrice=" + this.getSubscriptionPrice() + ", duration=" + this.getDuration() + ", active=" + this.getActive() + ", memberSubscriptions=" + this.getMemberSubscriptions() + ")";
    }

}
