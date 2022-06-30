package com.ironhack.Midterm.Project.controller.account.dto;

import java.math.BigDecimal;

public class MoneyDTO {
    private BigDecimal balance;

    public MoneyDTO() {
    }

    public MoneyDTO(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
