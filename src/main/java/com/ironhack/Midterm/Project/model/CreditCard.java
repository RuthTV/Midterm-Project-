package com.ironhack.Midterm.Project.model;

import com.ironhack.Midterm.Project.model.users.User;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class CreditCard extends Account{
    private BigDecimal interestRate;
    private BigDecimal creditLimit;

    public CreditCard() {
    }

    public CreditCard(long id, BigDecimal balance, String secretKey, User primaryOwner, Date creationDate, BigDecimal interestRate, BigDecimal creditLimit) {
        super(id, balance, secretKey, primaryOwner, creationDate);
        this.interestRate = interestRate;
        this.creditLimit = creditLimit;
    }

    public CreditCard(long id, BigDecimal balance, String secretKey, User primaryOwner, User secondayOwner, Date creationDate, BigDecimal interestRate, BigDecimal creditLimit) {
        super(id, balance, secretKey, primaryOwner, secondayOwner, creationDate);
        this.interestRate = interestRate;
        this.creditLimit = creditLimit;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public BigDecimal getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(BigDecimal creditLimit) {
        this.creditLimit = creditLimit;
    }
}
