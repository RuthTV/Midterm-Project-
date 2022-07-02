package com.ironhack.Midterm.Project.model.accounts;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ironhack.Midterm.Project.enums.Status;
import com.ironhack.Midterm.Project.model.money.Money;
import com.ironhack.Midterm.Project.model.users.User;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Currency;

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
  //  @JsonBackReference
    private User primaryOwner;
    @ManyToOne
    @JoinColumn(name = "secondary_owner_id")
 //   @JsonBackReference
    private User secondayOwner;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "penaltyFee_amount")),
            @AttributeOverride(name = "currency", column = @Column(name = "penaltyFee_currency"))
    })
    private Money penaltyFee;
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
        this.penaltyFee = new Money(BigDecimal.valueOf(40), Currency.getInstance("USD"));
    }

    public Account(Money balance, String secretKey, User primaryOwner, User secondayOwner, Date creationDate) {
        this.balance = balance;
        this.secretKey = secretKey;
        this.primaryOwner = primaryOwner;
        this.secondayOwner = secondayOwner;
        this.creationDate = creationDate;
        this.status = Status.ACTIVE;
        this.penaltyFee = new Money(BigDecimal.valueOf(40), Currency.getInstance("USD"));
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

    public Money getPenaltyFee() {
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
