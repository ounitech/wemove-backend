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
    private String name;

    @Column(name = "price", nullable = false)
    private int price;

    public enum Duration {
        daily,
        monthly,
        year
    }

    @Column(name = "duration", nullable = false)
    @Enumerated(EnumType.STRING)
    private Duration duration;

    @Column(name = "active", nullable = false, columnDefinition = "boolean default 1")
    private boolean active;

    @OneToMany(mappedBy = "subscription")
    private Set<MemberSubscription> memberSubscriptions;

    public Subscription() {
    }

    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public int getPrice() {
        return this.price;
    }

    public Duration getDuration() {
        return this.duration;
    }

    public boolean getActive() {
        return this.active;
    }

    public Set<MemberSubscription> getMemberSubscriptions() {
        return this.memberSubscriptions;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public void setActive(boolean active) {
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
                .append(name, that.name)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(name)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("name", name)
                .append("price", price)
                .append("duration", duration)
                .append("active", active)
                .toString();
    }
}
