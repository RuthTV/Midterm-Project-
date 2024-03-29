package com.ironhack.Midterm.Project.model.role;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ironhack.Midterm.Project.model.users.User;

import javax.persistence.*;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "user")
    @JsonIgnore
    private User user;

    public Role() {
    }

    public Role(String name, User user) {
        this.name = name;
        this.user = user;
    }

    public Role(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
