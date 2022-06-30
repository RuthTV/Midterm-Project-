package com.ironhack.Midterm.Project.controller.account.dto;

import com.ironhack.Midterm.Project.enums.Status;

import java.math.BigDecimal;
import java.sql.Date;

public class SavingDTO {
    private BigDecimal money;
    private String secretKey;
    private Long primaryUserId1;
    private Long secundaryUserId2;
    private Date creationDate;
    public SavingDTO() {
    }

    public SavingDTO(BigDecimal money, String secretKey, Long primaryUserId1, Date creationDate) {
        this.money = money;
        this.secretKey = secretKey;
        this.primaryUserId1 = primaryUserId1;
        this.creationDate = creationDate;
    }

    public SavingDTO(BigDecimal money, String secretKey, Long primaryUserId1, Long secundaryUserId2, Date creationDate) {
        this.money = money;
        this.secretKey = secretKey;
        this.primaryUserId1 = primaryUserId1;
        this.secundaryUserId2 = secundaryUserId2;
        this.creationDate = creationDate;
    }
}
