package com.ironhack.Midterm.Project.model.users;

import com.ironhack.Midterm.Project.model.address.Address;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;
@Entity
@PrimaryKeyJoinColumn(name = "id")
public class AccountHolder extends User{
    @Column(name = "date_birth")
    private Date dateOfBirth;
    @ManyToOne
    @JoinColumn(name = "primary_address")
    private Address primaryAddress;
    private String mailingAddress;

    public AccountHolder() {
    }

    public AccountHolder(long id, String name, Date dateOfBirth, Address primaryAddress) {
        super(id, name);
        this.dateOfBirth = dateOfBirth;
        this.primaryAddress = primaryAddress;
    }

    public AccountHolder(long id, String name, Date dateOfBirth, Address primaryAddress, String mailingAddress) {
        super(id, name);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AccountHolder that = (AccountHolder) o;
        return dateOfBirth.equals(that.dateOfBirth) && primaryAddress.equals(that.primaryAddress) && Objects.equals(mailingAddress, that.mailingAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), dateOfBirth, primaryAddress, mailingAddress);
    }
}
