package com.ironhack.Midterm.Project.controller.account.dto;

import com.ironhack.Midterm.Project.model.accounts.Money;

import java.math.BigDecimal;

public class Transference {
    private BigDecimal money;
    private Long accountId1;
    private Long accountId2;

    public Transference() {
    }

    public Transference(BigDecimal money, Long accountId1, Long accountId2) {
        this.money = money;
        this.accountId1 = accountId1;
        this.accountId2 = accountId2;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Long getAccountId1() {
        return accountId1;
    }

    public void setAccountId1(Long accountId1) {
        this.accountId1 = accountId1;
    }

    public Long getAccountId2() {
        return accountId2;
    }

    public void setAccountId2(Long accountId2) {
        this.accountId2 = accountId2;
    }
}
