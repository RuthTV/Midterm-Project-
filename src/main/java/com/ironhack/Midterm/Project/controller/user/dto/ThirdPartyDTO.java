package com.ironhack.Midterm.Project.controller.user.dto;

import java.math.BigDecimal;

public class ThirdPartyDTO {
    private BigDecimal money;
    private Long accountId;
    private String secretKey;


    public ThirdPartyDTO() {
    }

    public ThirdPartyDTO(BigDecimal money, Long accountId, String secretKey) {
        this.money = money;
        this.accountId = accountId;
        this.secretKey = secretKey;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
}
