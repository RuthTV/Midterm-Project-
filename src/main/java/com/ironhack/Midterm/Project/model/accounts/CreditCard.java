package com.ironhack.Midterm.Project.model.accounts;

import com.ironhack.Midterm.Project.model.users.User;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Currency;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class CreditCard extends Account {
    @DecimalMax(value = "0.2")
    @DecimalMin(value = "0.1")
    private BigDecimal interestRate;
    private Date lastActualizedDate;
//    @Max(value = 100000)
//    @Min(value = 100)
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "credit_limit_amount")),
            @AttributeOverride(name = "currency", column = @Column(name = "credit_limit_currency"))
    })
    private Money creditLimit;
    @Embedded
    private Money balance;

    public CreditCard() {
    }

    public CreditCard(Money balance, String secretKey, User primaryOwner, Date creationDate) {
        super(balance, secretKey, primaryOwner, creationDate);
        this.creditLimit = new Money(BigDecimal.valueOf(100), Currency.getInstance("USD"));
        this.interestRate = BigDecimal.valueOf(0.2);
        this.lastActualizedDate = creationDate;
        setBalance(balance);
    }

    public CreditCard(Money balance, String secretKey, User primaryOwner, User secondayOwner, Date creationDate) {
        super(balance, secretKey, primaryOwner, secondayOwner, creationDate);
        this.creditLimit = new Money(BigDecimal.valueOf(100), Currency.getInstance("USD"));
        this.interestRate = BigDecimal.valueOf(0.2);
        this.lastActualizedDate = creationDate;
        setBalance(balance);
    }

    public CreditCard(Money balance, String secretKey, User primaryOwner, BigDecimal interestRate, Date creationDate) {
        super(balance, secretKey, primaryOwner, creationDate);
        this.lastActualizedDate = creationDate;
        setInterestRate(interestRate);
        this.creditLimit = new Money(BigDecimal.valueOf(100), Currency.getInstance("USD"));
        setBalance(balance);
    }

    public CreditCard(Money balance, String secretKey, User primaryOwner, User secondayOwner, BigDecimal interestRate, Date creationDate) {
        super(balance, secretKey, primaryOwner, secondayOwner, creationDate);
        this.lastActualizedDate = creationDate;
        setInterestRate(interestRate);
        this.creditLimit = new Money(BigDecimal.valueOf(100), Currency.getInstance("USD"));
        setBalance(balance);
    }

    public CreditCard(Money balance, String secretKey, User primaryOwner, Date creationDate, Money creditLimit) {
        super(balance, secretKey, primaryOwner, creationDate);
        this.lastActualizedDate = creationDate;
        this.interestRate = BigDecimal.valueOf(0.2);
        setCreditLimit(creditLimit);
        setBalance(balance);
    }

    public CreditCard(Money balance, String secretKey, User primaryOwner, User secondayOwner, Date creationDate, Money creditLimit) {
        super(balance, secretKey, primaryOwner, secondayOwner, creationDate);
        this.lastActualizedDate = creationDate;
        this.interestRate = BigDecimal.valueOf(0.2);
        setCreditLimit(creditLimit);
        setBalance(balance);
    }
    public CreditCard(Money balance, String secretKey, User primaryOwner, BigDecimal interestRate, Date creationDate,  Money creditLimit) {
        super(balance, secretKey, primaryOwner, creationDate);
        this.lastActualizedDate = creationDate;
        setInterestRate(interestRate);
        setCreditLimit(creditLimit);
        setBalance(balance);
    }

    public CreditCard(Money balance, String secretKey, User primaryOwner, User secondayOwner, BigDecimal interestRate, Date creationDate, Money creditLimit) {
        super(balance, secretKey, primaryOwner, secondayOwner, creationDate);
        this.lastActualizedDate = creationDate;
        setInterestRate(interestRate);
        setCreditLimit(creditLimit);
        setBalance(balance);
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

    public Money getCreditLimit() {
        return creditLimit;
    }

    public String setCreditLimit(Money creditLimit) {
        if (creditLimit.getAmount().compareTo(BigDecimal.valueOf(100)) == -1){
            this.creditLimit = new Money(BigDecimal.valueOf(100), Currency.getInstance("USD"));
            return "The minimum value of credit limit is 100\n " +
                    "Your credit limit has been set at 100";
        } else if (creditLimit.getAmount().compareTo(BigDecimal.valueOf(100000)) == 1) {
            this.creditLimit = new Money(BigDecimal.valueOf(100000), Currency.getInstance("USD"));
            return "The maximum value of credit limit is 100000\n" +
                    "Your credit limit has been set at 100000";
        }else {
            this.creditLimit = creditLimit;
            return "The credit limit has been set";
        }
    }

    public Date getLastActualizedDate() {
        return lastActualizedDate;
    }

    public void setLastActualizedDate(Date lastActualizedDate) {
        this.lastActualizedDate = lastActualizedDate;
    }

    public Money getBalance(Date lastActualizedDate) {
        balanceActualized(lastActualizedDate);
        return balance;
    }

    public String setBalance(Money balance) {
        this.balance = balance;
        return "The balance has been set";
    }

    public void balanceActualized(Date lastActualizedDate){
        Money money;
        LocalDate today = LocalDate.now();
        LocalDate lastDateActualizedLocal = lastActualizedDate.toLocalDate();
        if(today.isAfter(lastDateActualizedLocal.plusMonths(1))){
            lastDateActualizedLocal = lastDateActualizedLocal.plusMonths(1);
            setLastActualizedDate(Date.valueOf(lastDateActualizedLocal));
            BigDecimal interest = getInterestRate().divide(BigDecimal.valueOf(12), 2, RoundingMode.HALF_UP);
            money = new Money(balance.getAmount().add(interest.multiply(balance.getAmount()).setScale(2, RoundingMode.HALF_UP)), Currency.getInstance("USD"));
            setBalance(money);
        }
    }
}
