package com.ironhack.Midterm.Project.controller.account.dto;

import com.ironhack.Midterm.Project.enums.Status;

import java.math.BigDecimal;
import java.sql.Date;

public class StudentCheckingDTO {

    private BigDecimal money;
    private String secretKey;
    private Long primaryUserId1;
    private Long secundaryUserId2;
    private Date creationDate;
    public StudentCheckingDTO() {
    }

    public StudentCheckingDTO(BigDecimal money, String secretKey, Long primaryUserId1, Date creationDate) {
        this.money = money;
        this.secretKey = secretKey;
        this.primaryUserId1 = primaryUserId1;
        this.creationDate = creationDate;
    }

    public StudentCheckingDTO(BigDecimal money, String secretKey, Long primaryUserId1, Long secundaryUserId2, Date creationDate) {
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
}
