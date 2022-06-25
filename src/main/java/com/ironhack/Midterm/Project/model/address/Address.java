package com.ironhack.Midterm.Project.model.address;

import com.ironhack.Midterm.Project.model.users.AccountHolder;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Address {
    @Id
    private long id;
    private String street;
    private int postCode;
    @OneToMany(mappedBy = "primaryAddress", fetch = FetchType.EAGER)
    private List<AccountHolder> accountHolder;

    public List<AccountHolder> getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(List<AccountHolder> accountHolder) {
        this.accountHolder = accountHolder;
    }

    public Address() {
    }

    public Address(long id,String street, int postCode) {
        this.id = id;
        this.street = street;
        this.postCode = postCode;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getPostCode() {
        return postCode;
    }

    public void setPostCode(int postCode) {
        this.postCode = postCode;
    }

    @Override
    public String toString() {
        return "Address{" +
   //             "id=" + id +
                ", street='" + street + '\'' +
                ", postCode=" + postCode +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return postCode == address.postCode && street.equals(address.street);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, postCode);
    }
}
