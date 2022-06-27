package com.ironhack.Midterm.Project.model;

import com.ironhack.Midterm.Project.model.address.Address;
import com.ironhack.Midterm.Project.model.users.AccountHolder;
import com.ironhack.Midterm.Project.model.users.Admin;
import com.ironhack.Midterm.Project.model.users.User;
import com.ironhack.Midterm.Project.repository.AccountRepository;
import com.ironhack.Midterm.Project.repository.AddressRepository;
import com.ironhack.Midterm.Project.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CreditCardTest {
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AddressRepository addressRepository;
    CreditCard creditCard, creditCard1;
    User user1, user2;
    Address address;

    @BeforeEach
    void setUp() {
        address = new Address(1, "Ambasaguas 55", 48891);
        user1 = new Admin(1, "Julen Telleria");
        user2 = new AccountHolder(2, "Julen Telleria", Date.valueOf("1991-12-12"), address);
        creditCard = new CreditCard(1, BigDecimal.valueOf(2000000), "fngmhg_fhª", user1, BigDecimal.valueOf(0.15), Date.valueOf("2018-01-23"));
        creditCard1 = new CreditCard(2, BigDecimal.valueOf(45000), "ffzhgsjª", user2, BigDecimal.valueOf(0.1), Date.valueOf("2019-01-23"), BigDecimal.valueOf(1030));
        accountRepository.saveAll(List.of(creditCard, creditCard1));
    }

    @AfterEach
    void tearDown() {
        accountRepository.deleteAll();
    }

    @Test
    void setInterestRate_InterestRate_SameValue() {
        assertEquals(BigDecimal.valueOf(0.15), creditCard.getInterestRate());
        assertEquals(BigDecimal.valueOf(0.1), creditCard1.getInterestRate());
        assertEquals("The interest rate has been set", creditCard.setInterestRate(BigDecimal.valueOf(0.2)));
    }

    @Test
    void setInterestRate_InterestRate_DefaultValue() {
        assertEquals("The minimum value of interest rate is 0.1\n" +
                "Your interest rate has been set at 0.1", creditCard.setInterestRate(BigDecimal.valueOf(0.04)));
        assertEquals("The maximum value of interest rate is 0.2\n" +
                "Your interest rate has been set at 0.2", creditCard1.setInterestRate(BigDecimal.valueOf(0.5)));
        assertEquals(BigDecimal.valueOf(0.1), creditCard.getInterestRate());
        assertEquals(BigDecimal.valueOf(0.2), creditCard1.getInterestRate());
    }

    @Test
    void setCreditLimit_CreditLimit_SameValue() {
        assertEquals(BigDecimal.valueOf(1030), creditCard1.getCreditLimit());
        assertEquals("The credit limit has been set", creditCard1.setCreditLimit(BigDecimal.valueOf(10870)));
    }

    @Test
    void setCreditLimit_CreditLimit_DefaultValue() {
        assertEquals("The maximum value of credit limit is 100000\n" +
                "Your credit limit has been set at 100000", creditCard.setCreditLimit(BigDecimal.valueOf(100000000)));
        assertEquals("The minimum value of credit limit is 100\n " +
                "Your credit limit has been set at 100", creditCard1.setCreditLimit(BigDecimal.valueOf(-45)));
        assertEquals(BigDecimal.valueOf(100000), creditCard.getCreditLimit());
        assertEquals(BigDecimal.valueOf(100), creditCard1.getCreditLimit());
    }
}