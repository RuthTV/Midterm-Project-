package com.ironhack.Midterm.Project.model.accounts;

import com.ironhack.Midterm.Project.model.accounts.Checking;
import com.ironhack.Midterm.Project.model.accounts.CreditCard;
import com.ironhack.Midterm.Project.model.accounts.Money;
import com.ironhack.Midterm.Project.model.address.Address;
import com.ironhack.Midterm.Project.model.users.AccountHolder;
import com.ironhack.Midterm.Project.model.users.Admin;
import com.ironhack.Midterm.Project.repositories.accountRepository.CreditCardRepository;
import com.ironhack.Midterm.Project.repositories.userRepository.AccountHolderRepository;
import com.ironhack.Midterm.Project.repositories.userRepository.AdminRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CreditCardTest {
    @Autowired
    CreditCardRepository creditCardRepository;

    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private AccountHolderRepository accountHolderRepository;
    private CreditCard creditCard, creditCard1;
    private Admin user1;
    private AccountHolder user2;
    private Address address;
    private Money money, money2;

    @BeforeEach
    void setUp() {
        address = new Address("Ambasaguas 55", 48891);
        user1 = new Admin("Julen Telleria", "cbmnchmhc");
        money = new Money(BigDecimal.valueOf(2000000));
        money2 = new Money(BigDecimal.valueOf(245000));
        user2 = new AccountHolder("Julen Telleria", "dngmfhmf", Date.valueOf("1991-12-12"), address);
        creditCard = new CreditCard(money, "fngmhg_fhª", user1, BigDecimal.valueOf(0.15), Date.valueOf("2018-01-23"));
        creditCard1 = new CreditCard(money2, "fzhgnhª", user2, BigDecimal.valueOf(0.1), Date.valueOf("2016-12-23"));
        adminRepository.save(user1);
        accountHolderRepository.save(user2);
        creditCardRepository.saveAll(List.of(creditCard, creditCard1));

    }

    @AfterEach
    void tearDown() {
        creditCardRepository.deleteAll();
        accountHolderRepository.deleteAll();
        adminRepository.deleteAll();
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
        creditCard1.setCreditLimit(BigDecimal.valueOf(1030));
        assertEquals(BigDecimal.valueOf(1030), creditCard1.getCreditLimit());
        assertEquals("The credit limit has been set", creditCard1.setCreditLimit(BigDecimal.valueOf(1030)));
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
    @Test
    void getBalance_LastActualizedDateMore1month(){
        Money money3 = new Money(BigDecimal.valueOf(19000));
        CreditCard creditCard2 = new CreditCard(money3, "fngmhg_fhª", user1, Date.valueOf("2022-03-03"));
        assertEquals(BigDecimal.valueOf(19380).setScale(2), creditCard2.getBalance(creditCard2.getLastActualizedDate()).getBalance());
        creditCard2.getBalance(creditCard2.getLastActualizedDate());
        assertEquals(Date.valueOf("2022-05-03"), creditCard2.getLastActualizedDate());

    }
}