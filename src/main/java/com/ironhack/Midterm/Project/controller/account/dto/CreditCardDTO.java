package com.ironhack.Midterm.Project.controller.account.dto;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.sql.Date;

public class CreditCardDTO {
    private BigDecimal money;
    private String secretKey;
    private Long primaryUserId1;
    private Long secundaryUserId2 = 0L;
    private Date creationDate;
    @DecimalMax(value = "0.2")
    @DecimalMin(value = "0.1")
    private BigDecimal interestRate;
    @Max(value = 100000)
    @Min(value = 100)
    private BigDecimal creditLimit;

    public CreditCardDTO() {
    }

    public CreditCardDTO(BigDecimal money, String secretKey, Long primaryUserId1, Date creationDate) {
        this.money = money;
        this.secretKey = secretKey;
        this.primaryUserId1 = primaryUserId1;
        this.creationDate = creationDate;
        this.creditLimit = BigDecimal.valueOf(100);
        this.interestRate = BigDecimal.valueOf(0.2);
    }

    public CreditCardDTO(BigDecimal money, String secretKey, Long primaryUserId1, BigDecimal interestRate, Date creationDate) {
        this.money = money;
        this.secretKey = secretKey;
        this.primaryUserId1 = primaryUserId1;
        this.creationDate = creationDate;
        this.interestRate = interestRate;
        this.creditLimit = BigDecimal.valueOf(100);
    }

    public CreditCardDTO(BigDecimal money, String secretKey, Long primaryUserId1, Date creationDate, BigDecimal creditLimit) {
        this.money = money;
        this.secretKey = secretKey;
        this.primaryUserId1 = primaryUserId1;
        this.creationDate = creationDate;
        this.creditLimit = creditLimit;
        this.interestRate = BigDecimal.valueOf(0.2);
    }

    public CreditCardDTO(BigDecimal money, String secretKey, Long primaryUserId1, Date creationDate, BigDecimal interestRate, BigDecimal creditLimit) {
        this.money = money;
        this.secretKey = secretKey;
        this.primaryUserId1 = primaryUserId1;
        this.creationDate = creationDate;
        this.interestRate = interestRate;
        this.creditLimit = creditLimit;
    }

    public CreditCardDTO(BigDecimal money, String secretKey, Long primaryUserId1, Long secundaryUserId2, BigDecimal interestRate, Date creationDate) {
        this.money = money;
        this.secretKey = secretKey;
        this.primaryUserId1 = primaryUserId1;
        this.secundaryUserId2 = secundaryUserId2;
        this.creationDate = creationDate;
        this.interestRate = interestRate;
        this.creditLimit = BigDecimal.valueOf(100);
    }

    public CreditCardDTO(BigDecimal money, String secretKey, Long primaryUserId1, Long secundaryUserId2, Date creationDate) {
        this.money = money;
        this.secretKey = secretKey;
        this.primaryUserId1 = primaryUserId1;
        this.secundaryUserId2 = secundaryUserId2;
        this.creationDate = creationDate;
        this.creditLimit = BigDecimal.valueOf(100);
        this.interestRate = BigDecimal.valueOf(0.2);
    }

    public CreditCardDTO(BigDecimal money, String secretKey, Long primaryUserId1, Long secundaryUserId2, Date creationDate, BigDecimal creditLimit) {
        this.money = money;
        this.secretKey = secretKey;
        this.primaryUserId1 = primaryUserId1;
        this.secundaryUserId2 = secundaryUserId2;
        this.creationDate = creationDate;
        this.creditLimit = creditLimit;
        this.interestRate = BigDecimal.valueOf(0.2);
    }

    public CreditCardDTO(BigDecimal money, String secretKey, Long primaryUserId1, Long secundaryUserId2, Date creationDate, BigDecimal interestRate, BigDecimal creditLimit) {
        this.money = money;
        this.secretKey = secretKey;
        this.primaryUserId1 = primaryUserId1;
        this.secundaryUserId2 = secundaryUserId2;
        this.creationDate = creationDate;
        this.interestRate = interestRate;
        this.creditLimit = creditLimit;
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

    public BigDecimal getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(BigDecimal creditLimit) {
        this.creditLimit = creditLimit;
    }
}
