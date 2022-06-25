package com.ironhack.Midterm.Project.model.users;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Admin extends User{
    public Admin() {
    }

    public Admin(long id, String firstName, String lastName) {
        super(id, firstName, lastName);
    }

}
