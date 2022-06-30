package com.ironhack.Midterm.Project.model.accounts;

import com.ironhack.Midterm.Project.model.users.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Checking extends Account {
    private BigDecimal monthlyMaintenanceFee;
    private BigDecimal minimumBalance;
    @Embedded
    private Money balance;

    @NotNull
    private Date lastActualizedDate;
    public Checking() {
    }

    public Checking(Money balance, String secretKey, User primaryOwner, Date creationDate) {
        super(balance, secretKey, primaryOwner, creationDate);
        this.monthlyMaintenanceFee = BigDecimal.valueOf(12);
        this.minimumBalance = BigDecimal.valueOf(250);
        this.lastActualizedDate = creationDate;
        setBalance(balance);
    }

    public Checking(Money balance, String secretKey, User primaryOwner, User secondayOwner, Date creationDate) {
        super(balance, secretKey, primaryOwner, secondayOwner, creationDate);
        this.monthlyMaintenanceFee = BigDecimal.valueOf(12);
        this.minimumBalance = BigDecimal.valueOf(250);
        this.lastActualizedDate = creationDate;
        setBalance(balance);
    }

    public BigDecimal getMonthlyMaintenanceFee() {
        return monthlyMaintenanceFee;
    }

    public BigDecimal getMinimumBalance() {
        return minimumBalance;
    }

    public Money getBalance(Date lastActualizedDate){
        setLastActualizedDate(lastActualizedDate);
        balanceActualized(lastActualizedDate);
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

    public Date getLastActualizedDate() {
        return lastActualizedDate;
    }

    public void setLastActualizedDate(Date lastActualizedDate) {
        this.lastActualizedDate = lastActualizedDate;
    }

    public void balanceActualized(Date lastActualizedDate){
        LocalDate today = LocalDate.now();
        LocalDate lastDateActualizedLocal = lastActualizedDate.toLocalDate();
        if(today.isAfter(lastDateActualizedLocal.plusMonths(1))){
            setLastActualizedDate(Date.valueOf(lastDateActualizedLocal.plusMonths(1)));
            Money money = new Money(getBalance().getBalance().subtract(monthlyMaintenanceFee));
            setBalance(money);
        }
    }
}
