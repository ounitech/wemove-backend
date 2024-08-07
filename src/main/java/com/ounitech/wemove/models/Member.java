package com.ounitech.wemove.models;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "firstname", nullable = false)
    private String firstname;

    @Column(name = "lastname", nullable = false)
    private String lastname;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "gender")
    private String gender;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "picture")
    private String picture;

    @Column(name = "active", nullable = false, columnDefinition = "BYTE DEFAULT 0")
    private Byte active;

//    @OneToOne
//    @JoinColumn(name = "memberSubid", referencedColumnName = "id")
//    private MemberSubscription memberSubscription;

    @OneToMany(mappedBy = "member")
    private Set<Entry> entries;

    public Member() {
    }

    public Integer getId() {
        return this.id;
    }

    public String getFirstname() {
        return this.firstname;
    }

    public String getLastname() {
        return this.lastname;
    }

    public String getEmail() {
        return this.email;
    }

    public String getGender() {
        return this.gender;
    }

    public String getAddress() {
        return this.address;
    }

    public String getPhone() {
        return this.phone;
    }

    public String getPicture() {
        return this.picture;
    }

    public Byte getActive() {
        return this.active;
    }

    public Set<Entry> getEntries() {
        return this.entries;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setActive(Byte active) {
        this.active = active;
    }

    public void setEntries(Set<Entry> entries) {
        this.entries = entries;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Member)) return false;
        final Member other = (Member) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$firstname = this.getFirstname();
        final Object other$firstname = other.getFirstname();
        if (this$firstname == null ? other$firstname != null : !this$firstname.equals(other$firstname)) return false;
        final Object this$lastname = this.getLastname();
        final Object other$lastname = other.getLastname();
        if (this$lastname == null ? other$lastname != null : !this$lastname.equals(other$lastname)) return false;
        final Object this$email = this.getEmail();
        final Object other$email = other.getEmail();
        if (this$email == null ? other$email != null : !this$email.equals(other$email)) return false;
        final Object this$gender = this.getGender();
        final Object other$gender = other.getGender();
        if (this$gender == null ? other$gender != null : !this$gender.equals(other$gender)) return false;
        final Object this$address = this.getAddress();
        final Object other$address = other.getAddress();
        if (this$address == null ? other$address != null : !this$address.equals(other$address)) return false;
        final Object this$phone = this.getPhone();
        final Object other$phone = other.getPhone();
        if (this$phone == null ? other$phone != null : !this$phone.equals(other$phone)) return false;
        final Object this$picture = this.getPicture();
        final Object other$picture = other.getPicture();
        if (this$picture == null ? other$picture != null : !this$picture.equals(other$picture)) return false;
        final Object this$active = this.getActive();
        final Object other$active = other.getActive();
        if (this$active == null ? other$active != null : !this$active.equals(other$active)) return false;
        final Object this$entries = this.getEntries();
        final Object other$entries = other.getEntries();
        if (this$entries == null ? other$entries != null : !this$entries.equals(other$entries)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Member;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $firstname = this.getFirstname();
        result = result * PRIME + ($firstname == null ? 43 : $firstname.hashCode());
        final Object $lastname = this.getLastname();
        result = result * PRIME + ($lastname == null ? 43 : $lastname.hashCode());
        final Object $email = this.getEmail();
        result = result * PRIME + ($email == null ? 43 : $email.hashCode());
        final Object $gender = this.getGender();
        result = result * PRIME + ($gender == null ? 43 : $gender.hashCode());
        final Object $address = this.getAddress();
        result = result * PRIME + ($address == null ? 43 : $address.hashCode());
        final Object $phone = this.getPhone();
        result = result * PRIME + ($phone == null ? 43 : $phone.hashCode());
        final Object $picture = this.getPicture();
        result = result * PRIME + ($picture == null ? 43 : $picture.hashCode());
        final Object $active = this.getActive();
        result = result * PRIME + ($active == null ? 43 : $active.hashCode());
        final Object $entries = this.getEntries();
        result = result * PRIME + ($entries == null ? 43 : $entries.hashCode());
        return result;
    }

    public String toString() {
        return "Member(id=" + this.getId() + ", firstname=" + this.getFirstname() + ", lastname=" + this.getLastname() + ", email=" + this.getEmail() + ", gender=" + this.getGender() + ", address=" + this.getAddress() + ", phone=" + this.getPhone() + ", picture=" + this.getPicture() + ", active=" + this.getActive() + ", entries=" + this.getEntries() + ")";
    }
}