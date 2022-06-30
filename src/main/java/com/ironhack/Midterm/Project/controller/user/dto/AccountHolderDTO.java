package com.ironhack.Midterm.Project.controller.user.dto;

import com.ironhack.Midterm.Project.model.address.Address;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;

public class AccountHolderDTO extends UserDTO{
    @Embedded
    private Address primaryAddress;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "street", column = @Column(name = "mailing_street")),
            @AttributeOverride(name = "postCode", column = @Column(name = "mailing_postal_code"))
    })
    private Address mailingAddress;

    public AccountHolderDTO() {
    }

    public AccountHolderDTO(String name, Address primaryAddress) {
        super(name);
        this.primaryAddress = primaryAddress;
    }

    public AccountHolderDTO(String name, Address primaryAddress, Address mailingAddress) {
        super(name);
        this.primaryAddress = primaryAddress;
        this.mailingAddress = mailingAddress;
    }

    public Address getPrimaryAddress() {
        return primaryAddress;
    }

    public void setPrimaryAddress(Address primaryAddress) {
        this.primaryAddress = primaryAddress;
    }

    public Address getMailingAddress() {
        return mailingAddress;
    }

    public void setMailingAddress(Address mailingAddress) {
        this.mailingAddress = mailingAddress;
    }
}
