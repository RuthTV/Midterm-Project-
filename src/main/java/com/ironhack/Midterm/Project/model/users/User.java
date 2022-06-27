package com.ironhack.Midterm.Project.model.users;

import com.ironhack.Midterm.Project.model.Account;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class User {
    @Id
    private long id;
    private String name;

    public User() {
    }

    public User(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}