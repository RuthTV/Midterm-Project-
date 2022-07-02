package com.ironhack.Midterm.Project.controller.user.dto;

import java.math.BigDecimal;

public class ThirdPartyDTO {
    private BigDecimal money;
    private Long accountId;
    private String secretKey;
    private String hashedKey;


    public ThirdPartyDTO() {
    }

    public ThirdPartyDTO(BigDecimal money, Long accountId, String secretKey, String hashedKey) {
        this.money = money;
        this.accountId = accountId;
        this.secretKey = secretKey;
        this.hashedKey = hashedKey;
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

    public String getHashedKey() {
        return hashedKey;
    }

    public void setHashedKey(String hashedKey) {
        this.hashedKey = hashedKey;
    }
}
