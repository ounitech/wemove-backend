package com.ounitech.wemove.models;


import jakarta.persistence.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Set;

@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "name")
    private String roleName;

    @OneToMany(mappedBy = "role")
    private Set<Staff> staffs;

    public Role() {
    }

    public Integer getId() {
        return this.id;
    }

    public String getRoleName() {
        return this.roleName;
    }

    public Set<Staff> getStaffs() {
        return this.staffs;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public void setStaffs(Set<Staff> staffs) {
        this.staffs = staffs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Role role)) {
            return false;
        }

        return new EqualsBuilder()
                .append(roleName, role.roleName)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(roleName)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("roleName", roleName)
                .toString();
    }
}
