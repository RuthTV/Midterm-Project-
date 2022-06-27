package com.ironhack.Midterm.Project.model;

import com.ironhack.Midterm.Project.model.users.User;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Checking extends Account{
    private BigDecimal monthlyMaintenanceFee;
    private BigDecimal minimumBalance;
    private BigDecimal balance;

    public Checking() {
    }

    public Checking(long id, BigDecimal balance, String secretKey, User primaryOwner, Date creationDate) {
        super(id, balance, secretKey, primaryOwner, creationDate);
        this.monthlyMaintenanceFee = BigDecimal.valueOf(12);
        this.minimumBalance = BigDecimal.valueOf(250);
        setBalance(balance);
    }

    public Checking(long id, BigDecimal balance, String secretKey, User primaryOwner, User secondayOwner, Date creationDate) {
        super(id, balance, secretKey, primaryOwner, secondayOwner, creationDate);
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

    public BigDecimal getBalance(){
        return this.balance;
    }

    public String setBalance(BigDecimal balance){
        this.balance = balance;
        if(balance.compareTo(minimumBalance) == -1) {
            this.balance = getBalance().subtract(getPenaltyFee());
            return "A penalty has been taken from the balance";
        }else {
            this.balance = balance;
            return "The balance has been set";
        }
    }
}
