package com.ironhack.Midterm.Project.controller.account.dto;

import com.ironhack.Midterm.Project.model.accounts.Money;

public class Transference {
    private Money money;
    private Long accountId1;
    private Long accountId2;

    public Transference() {
    }

    public Transference(Money money, Long accountId1, Long accountId2) {
        this.money = money;
        this.accountId1 = accountId1;
        this.accountId2 = accountId2;
    }

    public Money getMoney() {
        return money;
    }

    public void setMoney(Money money) {
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
