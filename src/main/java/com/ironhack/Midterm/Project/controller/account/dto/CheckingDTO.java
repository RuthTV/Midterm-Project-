package com.ironhack.Midterm.Project.controller.account.dto;

import java.math.BigDecimal;
import java.sql.Date;

public class CheckingDTO {
    private BigDecimal money;
    private String secretKey;
    private Long primaryUserId1;
    private Long secundaryUserId2 = 0L;
    private Date creationDate;


    private BigDecimal minimumBalance = BigDecimal.valueOf(250);
    public CheckingDTO() {
    }

    public CheckingDTO(BigDecimal money, String secretKey, Long primaryUserId1, Date creationDate) {
        this.money = money;
        this.secretKey = secretKey;
        this.primaryUserId1 = primaryUserId1;
        this.creationDate = creationDate;
    }

    public CheckingDTO(BigDecimal money, String secretKey, Long primaryUserId1, Long secundaryUserId2, Date creationDate) {
        this.money = money;
        this.secretKey = secretKey;
        this.primaryUserId1 = primaryUserId1;
        this.secundaryUserId2 = secundaryUserId2;
        this.creationDate = creationDate;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public Long getPrimaryUserId1() {
        return primaryUserId1;
    }

    public void setPrimaryUserId1(Long primaryUserId1) {
        this.primaryUserId1 = primaryUserId1;
    }

    public Long getSecundaryUserId2() {
        return secundaryUserId2;
    }

    public void setSecundaryUserId2(Long secundaryUserId2) {
        this.secundaryUserId2 = secundaryUserId2;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public BigDecimal getMinimumBalance() {
        return minimumBalance;
    }

    public void setMinimumBalance(BigDecimal minimumBalance) {
        this.minimumBalance = minimumBalance;
    }

    @Override
    public String toString() {
        return "CheckingDTO{" +
                "money=" + money +
                ", secretKey='" + secretKey + '\'' +
                ", primaryUserId1=" + primaryUserId1 +
                ", secundaryUserId2=" + secundaryUserId2 +
                ", creationDate=" + creationDate +
                ", minimumBalance=" + minimumBalance +
                '}';
    }
}
