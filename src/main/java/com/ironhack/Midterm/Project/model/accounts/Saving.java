package com.ironhack.Midterm.Project.model.accounts;

import com.ironhack.Midterm.Project.model.users.User;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.time.LocalDate;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Saving extends Account {
    @DecimalMax(value = "0.5")
    @DecimalMin(value = "0.0025")
    private BigDecimal interestRate;
    private Date lastActualizedDate;
    private BigDecimal minimumBalance = BigDecimal.valueOf(1000);
    @Embedded
   // @DecimalMin(value = "100.00", message = "balance can not be lower than 100")
    private Money balance;

    public Saving() {
    }

    public Saving(Money balance, String secretKey, User primaryOwner, Date creationDate) {
        super(balance, secretKey, primaryOwner, creationDate);
        this.interestRate = BigDecimal.valueOf(0.0025);
        this.lastActualizedDate = creationDate;
        setBalance(balance);
    }

    public Saving(Money balance, String secretKey, User primaryOwner, User secondayOwner, Date creationDate) {
        super(balance, secretKey, primaryOwner, secondayOwner, creationDate);
        this.interestRate = BigDecimal.valueOf(0.0025);
        this.lastActualizedDate = creationDate;
        setBalance(balance);
    }

    public Saving(Money balance, String secretKey, User primaryOwner, Date creationDate, BigDecimal interestRate) {
        super(balance, secretKey, primaryOwner, creationDate);
        this.lastActualizedDate = creationDate;
        setInterestRate(interestRate);
        setBalance(balance);
    }

    public Saving(Money balance, String secretKey, User primaryOwner, User secondayOwner, Date creationDate, BigDecimal interestRate) {
        super(balance, secretKey, primaryOwner, secondayOwner, creationDate);
        this.lastActualizedDate = creationDate;
        setInterestRate(interestRate);
        setBalance(balance);
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        if (interestRate.compareTo(BigDecimal.valueOf(0.0025)) == -1){
            this.interestRate = BigDecimal.valueOf(0.0025);
            System.out.println("The minimum value of interest rate is 0.0025\n " +
                    "Your interest rate has been set at 0.0025");
        } else if (interestRate.compareTo(BigDecimal.valueOf(0.5)) == 1) {
            this.interestRate = BigDecimal.valueOf(0.5);
            System.out.println("The maximum value of interest rate is 0.5\n" +
                    "Your interest rate has been set at 0.5");
        }else {
            this.interestRate = interestRate;
        }
    }

    public Money getBalance(Date lastActualizedDate){
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

    public Money getBalance() {
        return balance;
    }

    public Date getLastActualizedDate() {
        return lastActualizedDate;
    }

    public void setLastActualizedDate(Date lastActualizedDate) {
        this.lastActualizedDate = lastActualizedDate;
    }

    public void balanceActualized(Date lastActualizedDate){
        Money money;
        LocalDate today = LocalDate.now();
        LocalDate lastDateActualizedLocal = lastActualizedDate.toLocalDate();
        if(today.isAfter(lastDateActualizedLocal.plusYears(1))){
            setLastActualizedDate(Date.valueOf(lastDateActualizedLocal.plusYears(1)));
            BigDecimal interest = balance.getBalance().multiply(getInterestRate()).setScale(2, RoundingMode.HALF_UP);
            money = new Money(balance.getBalance().add(interest));
            setBalance(money);
        }
    }
}
