package com.ironhack.Midterm.Project.model.users;

import com.ironhack.Midterm.Project.model.role.Role;

import javax.persistence.*;
import java.util.Set;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Admin extends User{
    public Admin() {
    }

    public Admin(String username, String password) {
        super(username, password);
    }
    public void setRole(Set<Role> roles){
        Role user = new Role("USER");
        Role admin = new Role("ADMIN");
        roles.add(user);
    }

}
