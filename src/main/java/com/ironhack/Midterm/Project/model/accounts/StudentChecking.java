package com.ironhack.Midterm.Project.model.accounts;

import com.ironhack.Midterm.Project.model.money.Money;
import com.ironhack.Midterm.Project.model.users.User;

import javax.persistence.*;
import java.sql.Date;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class StudentChecking extends Account {

    public StudentChecking() {
    }

    public StudentChecking(Money balance, String secretKey, User primaryOwner, Date creationDate) {
        super(balance, secretKey, primaryOwner, creationDate);
    }

    public StudentChecking(Money balance, String secretKey, User primaryOwner, User secondayOwner, Date creationDate) {
        super(balance, secretKey, primaryOwner, secondayOwner, creationDate);
    }

}
