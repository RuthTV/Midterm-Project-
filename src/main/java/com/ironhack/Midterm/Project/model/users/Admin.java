package com.ironhack.Midterm.Project.model.users;

import javax.persistence.*;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Admin extends User{
    public Admin() {
    }

    public Admin(String username, String password) {
        super(username, password);
    }

}
