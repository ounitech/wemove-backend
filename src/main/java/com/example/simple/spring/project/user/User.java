package com.example.simple.spring.project.user;

import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
@Table(name = "users")
public class User {



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;
    @Column(name = "email")
    private String email;
    @Column(name = "job")
    private String job;


    public User() {
    }

    public User(String firstname, String lastname, String email, LocalDate dateOfBirth, int age) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.job = job;
    }

    public Integer getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
