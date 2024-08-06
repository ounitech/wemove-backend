package com.ounitech.wemove.models;


import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue
    private Integer id;

//    enum Roles {
//        Manager, ViceManager, Maintenance, Trainer
//    }

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

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Role)) return false;
        final Role other = (Role) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$roleName = this.getRoleName();
        final Object other$roleName = other.getRoleName();
        if (this$roleName == null ? other$roleName != null : !this$roleName.equals(other$roleName)) return false;
        final Object this$staffs = this.getStaffs();
        final Object other$staffs = other.getStaffs();
        if (this$staffs == null ? other$staffs != null : !this$staffs.equals(other$staffs)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Role;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $roleName = this.getRoleName();
        result = result * PRIME + ($roleName == null ? 43 : $roleName.hashCode());
        final Object $staffs = this.getStaffs();
        result = result * PRIME + ($staffs == null ? 43 : $staffs.hashCode());
        return result;
    }

    public String toString() {
        return "Role(id=" + this.getId() + ", roleName=" + this.getRoleName() + ", staffs=" + this.getStaffs() + ")";
    }
}
