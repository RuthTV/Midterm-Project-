package com.ironhack.Midterm.Project.model.accounts;

import com.ironhack.Midterm.Project.model.users.User;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Checking extends Account {
    private BigDecimal monthlyMaintenanceFee;
    private BigDecimal minimumBalance;
    @Embedded
    private Money balance;

    public Checking() {
    }

    public Checking(Money balance, String secretKey, User primaryOwner, Date creationDate) {
        super(balance, secretKey, primaryOwner, creationDate);
        this.monthlyMaintenanceFee = BigDecimal.valueOf(12);
        this.minimumBalance = BigDecimal.valueOf(250);
        setBalance(balance);
    }

    public Checking(long id, Money balance, String secretKey, User primaryOwner, User secondayOwner, Date creationDate) {
        super(balance, secretKey, primaryOwner, secondayOwner, creationDate);
        this.monthlyMaintenanceFee = BigDecimal.valueOf(12);
        this.minimumBalance = BigDecimal.valueOf(250);
        setBalance(balance);
    }

    public BigDecimal getMonthlyMaintenanceFee() {
        return monthlyMaintenanceFee;
    }

    public BigDecimal getMinimumBalance() {
        return minimumBalance;
    }

    public Money getBalance(){
        return this.balance;
    }

    public String setBalance(Money balance){
        this.balance = balance;
        if(balance.getBalance().compareTo(minimumBalance) == -1) {
            balance.setBalance(balance.getBalance().subtract(getPenaltyFee()));
            return "A penalty has been taken from the balance";
        }else {
            this.balance = balance;
            return "The balance has been set";
        }
    }
}
