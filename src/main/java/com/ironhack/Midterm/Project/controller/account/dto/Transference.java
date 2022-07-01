package com.ironhack.Midterm.Project.controller.account.dto;

import java.math.BigDecimal;

public class Transference {
    private BigDecimal money;
    private String name;
    private Long accountId2;

    public Transference() {
    }

    public Transference(BigDecimal money, String name, Long accountId2) {
        this.money = money;
        this.name = name;
        this.accountId2 = accountId2;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAccountId2() {
        return accountId2;
    }

    public void setAccountId2(Long accountId2) {
        this.accountId2 = accountId2;
    }
}

