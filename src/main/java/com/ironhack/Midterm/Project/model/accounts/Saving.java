package com.ironhack.Midterm.Project.model.accounts;

import com.ironhack.Midterm.Project.model.users.User;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Saving extends Account {
    private BigDecimal interestRate;

    public Saving() {
    }

    public Saving(Money balance, String secretKey, User primaryOwner, Date creationDate) {
        super(balance, secretKey, primaryOwner, creationDate);
        this.interestRate = BigDecimal.valueOf(0.0025);
    }

    public Saving(Money balance, String secretKey, User primaryOwner, User secondayOwner, Date creationDate) {
        super(balance, secretKey, primaryOwner, secondayOwner, creationDate);
        this.interestRate = BigDecimal.valueOf(0.0025);
    }

    public Saving(Money balance, String secretKey, User primaryOwner, Date creationDate, BigDecimal interestRate) {
        super(balance, secretKey, primaryOwner, creationDate);
        setInterestRate(interestRate);
    }

    public Saving(Money balance, String secretKey, User primaryOwner, User secondayOwner, Date creationDate, BigDecimal interestRate) {
        super(balance, secretKey, primaryOwner, secondayOwner, creationDate);
        setInterestRate(interestRate);
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        if (interestRate.compareTo(BigDecimal.valueOf(0.0025)) == -1){
            this.interestRate = BigDecimal.valueOf(0.0025);
            System.out.println("The minimum value of interest rate is 0.0025\n " +
                    "Your interest rate has been set at 0.0025");
        } else if (interestRate.compareTo(BigDecimal.valueOf(0.5)) == 1) {
            this.interestRate = BigDecimal.valueOf(0.5);
            System.out.println("The maximum value of interest rate is 0.5\n" +
                    "Your interest rate has been set at 0.5");
        }else {
            this.interestRate = interestRate;
        }
    }
}
