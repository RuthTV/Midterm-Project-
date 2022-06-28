package com.ironhack.Midterm.Project.model.accounts;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
@Embeddable
public class Money {
    private BigDecimal balance;

    public Money() {
    }

    public Money(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
