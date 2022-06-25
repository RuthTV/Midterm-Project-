package com.ironhack.Midterm.Project.model;

import com.ironhack.Midterm.Project.model.users.User;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class StudentChecking extends Account{
    public StudentChecking() {
    }

    public StudentChecking(long id, BigDecimal balance, String secretKey, User primaryOwner, Date creationDate) {
        super(id, balance, secretKey, primaryOwner, creationDate);
    }

    public StudentChecking(long id, BigDecimal balance, String secretKey, User primaryOwner, User secondayOwner, Date creationDate) {
        super(id, balance, secretKey, primaryOwner, secondayOwner, creationDate);
    }

}
