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

    public CreditCard(long id, BigDecimal balance, String secretKey, User primaryOwner, BigDecimal interestRate, Date creationDate) {
        super(id, balance, secretKey, primaryOwner, creationDate);
        setInterestRate(interestRate);
        this.creditLimit = BigDecimal.valueOf(100);
    }

    public CreditCard(long id, BigDecimal balance, String secretKey, User primaryOwner, User secondayOwner, BigDecimal interestRate, Date creationDate) {
        super(id, balance, secretKey, primaryOwner, secondayOwner, creationDate);
        setInterestRate(interestRate);
        this.creditLimit = BigDecimal.valueOf(100);
    }

    public CreditCard(long id, BigDecimal balance, String secretKey, User primaryOwner, Date creationDate, BigDecimal creditLimit) {
        super(id, balance, secretKey, primaryOwner, creationDate);
        this.interestRate = BigDecimal.valueOf(0.2);
        setCreditLimit(creditLimit);
    }

    public CreditCard(long id, BigDecimal balance, String secretKey, User primaryOwner, User secondayOwner, Date creationDate, BigDecimal creditLimit) {
        super(id, balance, secretKey, primaryOwner, secondayOwner, creationDate);
        this.interestRate = BigDecimal.valueOf(0.2);
        setCreditLimit(creditLimit);
    }
    public CreditCard(long id, BigDecimal balance, String secretKey, User primaryOwner, BigDecimal interestRate, Date creationDate,  BigDecimal creditLimit) {
        super(id, balance, secretKey, primaryOwner, creationDate);
        this.interestRate = interestRate;
        this.creditLimit = creditLimit;
    }

    public CreditCard(long id, BigDecimal balance, String secretKey, User primaryOwner, User secondayOwner, BigDecimal interestRate, Date creationDate, BigDecimal creditLimit) {
        super(id, balance, secretKey, primaryOwner, secondayOwner, creationDate);
        this.interestRate = interestRate;
        this.creditLimit = creditLimit;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public String setInterestRate(BigDecimal interestRate) {
        if (interestRate.compareTo(BigDecimal.valueOf(0.1)) == -1){
            this.interestRate = BigDecimal.valueOf(0.1);
            return "The minimum value of interest rate is 0.1\n" +
                    "Your interest rate has been set at 0.1";
        } else if (interestRate.compareTo(BigDecimal.valueOf(0.2)) == 1) {
            this.interestRate = BigDecimal.valueOf(0.2);
            return "The maximum value of interest rate is 0.2\n" +
                    "Your interest rate has been set at 0.2";
        }
        this.interestRate = interestRate;
        return "The interest rate has been set";
    }

    public BigDecimal getCreditLimit() {
        return creditLimit;
    }

    public String setCreditLimit(BigDecimal creditLimit) {
        if (creditLimit.compareTo(BigDecimal.valueOf(100)) == -1){
            this.creditLimit = BigDecimal.valueOf(100);
            return "The minimum value of credit limit is 100\n " +
                    "Your credit limit has been set at 100";
        } else if (creditLimit.compareTo(BigDecimal.valueOf(100000)) == 1) {
            this.creditLimit = BigDecimal.valueOf(100000);
            return "The maximum value of credit limit is 100000\n" +
                    "Your credit limit has been set at 100000";
        }else {
            this.creditLimit = creditLimit;
            return "The credit limit has been set";
        }
    }
}
