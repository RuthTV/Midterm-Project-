package com.ironhack.Midterm.Project.model.accounts;

import com.ironhack.Midterm.Project.model.money.Money;
import com.ironhack.Midterm.Project.model.users.User;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Currency;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Saving extends Account {
    @DecimalMax(value = "0.5")
    @DecimalMin(value = "0.0025")
    private BigDecimal interestRate;
    private Date lastActualizedDate;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "minimum_balance_amount")),
            @AttributeOverride(name = "currency", column = @Column(name = "minimum_balance_currency"))
    })
    private Money minimumBalance = new Money(BigDecimal.valueOf(1000), Currency.getInstance("USD"));

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
        return balanceActualized(lastActualizedDate);
    }

    public String setBalance(Money balance){
        if(balance.getAmount().compareTo(minimumBalance.getAmount()) == -1) {
            balance.setAmount(balance.getAmount().subtract(getPenaltyFee().getAmount()));
            return "A penalty has been taken from the balance";
        }else {
            super.setBalance(balance);
            return "The balance has been set";
        }
    }

    public Date getLastActualizedDate() {
        return lastActualizedDate;
    }

    public void setLastActualizedDate(Date lastActualizedDate) {
        this.lastActualizedDate = lastActualizedDate;
    }

    public Money balanceActualized(Date lastActualizedDate){
        Money money = new Money(Currency.getInstance("USD"));
        LocalDate today = LocalDate.now();
        LocalDate lastDateActualizedLocal = lastActualizedDate.toLocalDate();
        LocalDate lastDateActualizedLocalPlusMonth = lastDateActualizedLocal.plusYears(1);
        if (today.isAfter(lastDateActualizedLocalPlusMonth)){
            long diffTodayLastActualized = ChronoUnit.YEARS.between(lastDateActualizedLocal, today);
            setLastActualizedDate(Date.valueOf(lastDateActualizedLocal.plusYears(diffTodayLastActualized)));
            BigDecimal interest = getBalance().getAmount().multiply(getInterestRate()).multiply(BigDecimal.valueOf(diffTodayLastActualized)).setScale(2, RoundingMode.HALF_UP);
            money = new Money(getBalance().getAmount().add(interest), Currency.getInstance("USD"));

        }
        super.setBalance(money);
        return getBalance();
    }
}
