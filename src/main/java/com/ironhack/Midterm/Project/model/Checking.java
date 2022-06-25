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

    public Checking() {
    }

    public Checking(long id, BigDecimal balance, String secretKey, User primaryOwner, Date creationDate, BigDecimal monthlyMaintenanceFee, BigDecimal minimumBalance) {
        super(id, balance, secretKey, primaryOwner, creationDate);
        this.monthlyMaintenanceFee = monthlyMaintenanceFee;
        this.minimumBalance = minimumBalance;
    }

    public Checking(long id, BigDecimal balance, String secretKey, User primaryOwner, User secondayOwner, Date creationDate, BigDecimal monthlyMaintenanceFee, BigDecimal minimumBalance) {
        super(id, balance, secretKey, primaryOwner, secondayOwner, creationDate);
        this.monthlyMaintenanceFee = monthlyMaintenanceFee;
        this.minimumBalance = minimumBalance;
    }

    public BigDecimal getMonthlyMaintenanceFee() {
        return monthlyMaintenanceFee;
    }

    public void setMonthlyMaintenanceFee(BigDecimal monthlyMaintenanceFee) {
        this.monthlyMaintenanceFee = monthlyMaintenanceFee;
    }

    public BigDecimal getMinimumBalance() {
        return minimumBalance;
    }

    public void setMinimumBalance(BigDecimal minimumBalance) {
        this.minimumBalance = minimumBalance;
    }
}
