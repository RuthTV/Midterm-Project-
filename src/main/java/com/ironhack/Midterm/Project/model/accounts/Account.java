package com.ironhack.Midterm.Project.model.accounts;


import com.ironhack.Midterm.Project.enums.Status;
import com.ironhack.Midterm.Project.model.users.User;


import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    @NotNull(message = "Balance can no be null")
    private Money balance;
    @NotNull(message = "Key can no be null")
    private String secretKey;

    @ManyToOne
    @JoinColumn(name = "primary_owner_id")
    @NotNull(message = "There must be a owner")
    private User primaryOwner;
    @ManyToOne
    @JoinColumn(name = "secondary_owner_id")
    private User secondayOwner;
    private BigDecimal penaltyFee;
    private Date creationDate;
    @Enumerated(EnumType.STRING)
    private Status status;

    public Account() {
    }

    public Account(Money balance, String secretKey, User primaryOwner, Date creationDate) {
        this.balance = balance;
        this.secretKey = secretKey;
        this.primaryOwner = primaryOwner;
        this.creationDate = creationDate;
        this.status = Status.ACTIVE;
        this.penaltyFee = BigDecimal.valueOf(40);
    }

    public Account(Money balance, String secretKey, User primaryOwner, User secondayOwner, Date creationDate) {
        this.balance = balance;
        this.secretKey = secretKey;
        this.primaryOwner = primaryOwner;
        this.secondayOwner = secondayOwner;
        this.creationDate = creationDate;
        this.status = Status.ACTIVE;
        this.penaltyFee = BigDecimal.valueOf(40);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Money getBalance() {
        return balance;
    }

    public String setBalance(Money balance) {
        this.balance = balance;
        return "The balance has been set";
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public User getPrimaryOwner() {
        return primaryOwner;
    }

    public void setPrimaryOwner(User primaryOwner) {
        this.primaryOwner = primaryOwner;
    }

    public User getSecondayOwner() {
        return secondayOwner;
    }

    public void setSecondayOwner(User secondayOwner) {
        this.secondayOwner = secondayOwner;
    }

    public BigDecimal getPenaltyFee() {
        return penaltyFee;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Account{" +
                "balance=" + balance +
                ", secretKey='" + secretKey + '\'' +
                ", primaryOwner=" + primaryOwner +
                ", secondayOwner=" + secondayOwner +
                ", penaltyFee=" + penaltyFee +
                ", creationDate=" + creationDate +
                ", status=" + status +
                '}';
    }

}
