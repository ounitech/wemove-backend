package com.ounitech.wemove.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
@Table(name = "staff")
public class Staff {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "firstname", nullable = false)
    @Size(min = 3,max = 50)
    @Pattern(regexp = "[a-zA-Z ]+")
    private String firstname;

    @Column(name = "lastname", nullable = false)
    @Size(min = 3,max = 50)
    @Pattern(regexp = "[a-zA-Z ]+")
    private String lastname;

    @Column(name = "email", nullable = false, unique = true)
    @Email(regexp = ".+@.+\\..+")
    @Size(min = 3,max = 150)
    private String email;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "address", nullable = false)
    @Size(min = 3,max = 150)
    private String address;

    @Column(name = "phone", nullable = false)
    @Pattern(regexp = "[0-9- ]+")
    @Size(min = 3,max = 25)
    private String phone;

    @Column(name = "picture", nullable = false)
    @Size(min = 3,max = 255)
    private String picture;

    @Column(name = "active", nullable = false, columnDefinition = "boolean default 0")
    private boolean active;

    @ManyToOne
    @JoinColumn(name = "roleid", referencedColumnName = "id", nullable = false)
    private Role role;

    public enum Gender {
        Male,
        Female
    }

    public Staff() {
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

    public Gender getGender() {
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

    public boolean getActive() {
        return this.active;
    }

    public Role getRole() {
        return this.role;
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

    public void setGender(Gender gender) {
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

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Staff staff)) {
            return false;
        }

        return new EqualsBuilder()
                .append(firstname, staff.firstname)
                .append(lastname, staff.lastname)
                .append(email, staff.email)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(firstname)
                .append(lastname)
                .append(email)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("firstname", firstname)
                .append("lastname", lastname)
                .append("email", email)
                .append("gender", gender)
                .append("address", address)
                .append("phone", phone)
                .append("picture", picture)
                .append("active", active)
                .append("role", role.getRoleName())
                .toString();
    }
}
