package com.ironhack.Midterm.Project.model.accounts;

import com.ironhack.Midterm.Project.model.money.Money;
import com.ironhack.Midterm.Project.model.users.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Currency;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Checking extends Account {
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "monthly_maintenance_fee_amount")),
            @AttributeOverride(name = "currency", column = @Column(name = "monthly_maintenance_fee_currency"))
    })
    private Money monthlyMaintenanceFee;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "minimum_balance_amount")),
            @AttributeOverride(name = "currency", column = @Column(name = "minimum_balance_currency"))
    })
    private Money minimumBalance;

    @NotNull
    private Date lastActualizedDate;

    public void setMonthlyMaintenanceFee(Money monthlyMaintenanceFee) {
        this.monthlyMaintenanceFee = monthlyMaintenanceFee;
    }

    public Checking() {
    }

    public Checking(Money balance, String secretKey, User primaryOwner, Date creationDate) {
        super(balance, secretKey, primaryOwner, creationDate);
        this.monthlyMaintenanceFee = new Money(BigDecimal.valueOf(12), Currency.getInstance("USD"));
        this.minimumBalance =  new Money(BigDecimal.valueOf(250), Currency.getInstance("USD"));
        this.lastActualizedDate = creationDate;
        setBalance(balance);
    }

    public Checking(Money balance, String secretKey, User primaryOwner, User secondayOwner, Date creationDate) {
        super(balance, secretKey, primaryOwner, secondayOwner, creationDate);
        this.monthlyMaintenanceFee = new Money(BigDecimal.valueOf(12), Currency.getInstance("USD"));
        this.minimumBalance =  new Money(BigDecimal.valueOf(250), Currency.getInstance("USD"));
        this.lastActualizedDate = creationDate;
        setBalance(balance);
    }

    public Money getMonthlyMaintenanceFee() {
        return monthlyMaintenanceFee;
    }

    public Money getMinimumBalance() {
        return minimumBalance;
    }

    public Money getBalance(Date lastActualizedDate){
        balanceActualized(lastActualizedDate);
        return super.getBalance();
    }

    public String setBalance(Money balance){
        super.setBalance(balance);
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

    public void balanceActualized(Date lastActualizedDate){
        Money money = new Money(Currency.getInstance("USD"));
        LocalDate today = LocalDate.now();
        LocalDate lastDateActualizedLocal = lastActualizedDate.toLocalDate();
        if(today.isAfter(lastDateActualizedLocal.plusMonths(1))){
            long diffTodayLastActualized = ChronoUnit.MONTHS.between(lastDateActualizedLocal, today);
            setLastActualizedDate(Date.valueOf(lastDateActualizedLocal.plusMonths(diffTodayLastActualized)));
            BigDecimal realMaintenanceeFee = monthlyMaintenanceFee.getAmount().multiply(BigDecimal.valueOf(diffTodayLastActualized));
            money = new Money(getBalance().getAmount().subtract(realMaintenanceeFee), Currency.getInstance("USD"));
        }
        setBalance(money);
    }
}
