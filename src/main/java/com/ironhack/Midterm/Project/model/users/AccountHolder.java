package com.ironhack.Midterm.Project.model.users;

import com.ironhack.Midterm.Project.model.address.Address;

import javax.persistence.*;
import java.sql.Date;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class AccountHolder extends User{
    @Column(name = "date_birth")
    private Date dateOfBirth;
    @Embedded
    private Address primaryAddress;
    private String mailingAddress;

    public AccountHolder() {
    }

    public AccountHolder(String username, String password, Date dateOfBirth, Address primaryAddress) {
        super(username, password);
        this.dateOfBirth = dateOfBirth;
        this.primaryAddress = primaryAddress;
    }

    public AccountHolder(String username, String password, Date dateOfBirth, Address primaryAddress, String mailingAddress) {
        super(username, password);
        this.dateOfBirth = dateOfBirth;
        this.primaryAddress = primaryAddress;
        this.mailingAddress = mailingAddress;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Address getPrimaryAddress() {
        return primaryAddress;
    }

    public void setPrimaryAddress(Address primaryAddress) {
        this.primaryAddress = primaryAddress;
    }

    public String getMailingAddress() {
        return mailingAddress;
    }

    public void setMailingAddress(String mailingAddress) {
        this.mailingAddress = mailingAddress;
    }

    @Override
    public String toString() {
        return "AccountHolder{" +
                "dateOfBirth=" + dateOfBirth +
                ", primaryAddress='" + primaryAddress + '\'' +
                ", mailingAddress='" + mailingAddress + '\'' +
                '}';
    }

}
