package com.ironhack.Midterm.Project.model.users;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ironhack.Midterm.Project.model.accounts.Account;
import com.ironhack.Midterm.Project.model.role.Role;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Role> roles;
    @OneToMany(mappedBy = "primaryOwner", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JsonManagedReference
    private Set<Account> accounts;
    @OneToMany(mappedBy = "secondayOwner", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
 //   @JsonManagedReference
    private Set<Account> secondaryAccounts;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public Set<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }

    public Set<Account> getSecondaryAccounts() {
        return secondaryAccounts;
    }

    public void setSecondaryAccounts(Set<Account> secondaryAccounts) {
        this.secondaryAccounts = secondaryAccounts;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                ", accounts=" + accounts +
                ", secondaryAccounts=" + secondaryAccounts +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return username.equals(user.username) && password.equals(user.password) && roles.equals(user.roles) && accounts.equals(user.accounts) && Objects.equals(secondaryAccounts, user.secondaryAccounts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, roles, accounts, secondaryAccounts);
    }
}