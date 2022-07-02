package com.ironhack.Midterm.Project.controller.account.dto;

import javax.persistence.Embedded;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;
import java.sql.Date;

public class SavingDTO {
    @DecimalMin(value = "100.00", message = "balance can not be lower than 100")
    private BigDecimal money;
    private String secretKey;
    private Long primaryUserId1;
    private Long secundaryUserId2 = 0L;
    private Date creationDate;

    @DecimalMax(value = "0.5")
    @DecimalMin(value = "0.0025")
    private BigDecimal interestRate;
    @Embedded
    private BigDecimal minimumBalance = BigDecimal.valueOf(1000);
    public SavingDTO() {
    }

    public SavingDTO(BigDecimal money, String secretKey, Long primaryUserId1, Date creationDate) {
        this.money = money;
        this.secretKey = secretKey;
        this.primaryUserId1 = primaryUserId1;
        this.creationDate = creationDate;
        this.interestRate = BigDecimal.valueOf(0.0025);
    }

    public SavingDTO(BigDecimal money, String secretKey, Long primaryUserId1, Long secundaryUserId2, Date creationDate) {
        this.money = money;
        this.secretKey = secretKey;
        this.primaryUserId1 = primaryUserId1;
        this.secundaryUserId2 = secundaryUserId2;
        this.creationDate = creationDate;
        this.interestRate = BigDecimal.valueOf(0.0025);
    }

    public SavingDTO(BigDecimal money, String secretKey, Long primaryUserId1, Date creationDate, BigDecimal interestRate) {
        this.money = money;
        this.secretKey = secretKey;
        this.primaryUserId1 = primaryUserId1;
        this.creationDate = creationDate;
        this.interestRate = interestRate;
    }

    public SavingDTO(BigDecimal money, String secretKey, Long primaryUserId1, Long secundaryUserId2, Date creationDate, BigDecimal interestRate) {
        this.money = money;
        this.secretKey = secretKey;
        this.primaryUserId1 = primaryUserId1;
        this.secundaryUserId2 = secundaryUserId2;
        this.creationDate = creationDate;
        this.interestRate = interestRate;
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

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public BigDecimal getMinimumBalance() {
        return minimumBalance;
    }

    public void setMinimumBalance(BigDecimal minimumBalance) {
        this.minimumBalance = minimumBalance;
    }
}
