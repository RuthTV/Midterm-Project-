package com.ironhack.Midterm.Project.model;

import com.ironhack.Midterm.Project.model.users.User;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Saving extends Account{
    private BigDecimal interestRate;

    public Saving() {
    }

    public Saving(long id, BigDecimal balance, String secretKey, User primaryOwner, Date creationDate, BigDecimal interestRate) {
        super(id, balance, secretKey, primaryOwner, creationDate);
        this.interestRate = interestRate;
    }

    public Saving(long id, BigDecimal balance, String secretKey, User primaryOwner, User secondayOwner, Date creationDate, BigDecimal interestRate) {
        super(id, balance, secretKey, primaryOwner, secondayOwner, creationDate);
        this.interestRate = interestRate;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }
}
