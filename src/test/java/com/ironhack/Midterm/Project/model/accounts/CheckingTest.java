package com.ironhack.Midterm.Project.model.accounts;

import com.ironhack.Midterm.Project.model.money.Money;
import com.ironhack.Midterm.Project.model.address.Address;
import com.ironhack.Midterm.Project.model.users.AccountHolder;
import com.ironhack.Midterm.Project.model.users.Admin;
import com.ironhack.Midterm.Project.repositories.accountRepository.CheckingRepository;
import com.ironhack.Midterm.Project.repositories.userRepository.AccountHolderRepository;
import com.ironhack.Midterm.Project.repositories.userRepository.AdminRepository;
import com.ironhack.Midterm.Project.repositories.userRepository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Currency;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CheckingTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private AccountHolderRepository accountHolderRepository;
    @Autowired
    private CheckingRepository checkingRepository;
    private Checking checking, checking2;
    private Admin user1;
    private AccountHolder user2;
    private Address address;
    private Money money, money2;

    @BeforeEach
    void setUp() {
        address = new Address("Ambasaguas 55", 48891);
        user1 = new Admin("Julen Telleria", "cbmnchmhc");
        money = new Money(BigDecimal.valueOf(2000000), Currency.getInstance("USD"));
        money2 = new Money(BigDecimal.valueOf(245000), Currency.getInstance("USD"));
        user2 = new AccountHolder("Julen Telleria", "dngmfhmf", Date.valueOf("1991-12-12"), address);
        checking = new Checking(money, "fngmhg_fhª", user1, Date.valueOf("2018-01-23"));
        checking2 = new Checking(money2, "fzhgnhª", user2, Date.valueOf("2016-12-23"));
        adminRepository.save(user1);
        accountHolderRepository.save(user2);
        checkingRepository.saveAll(List.of(checking, checking2));
    }

    @AfterEach
    void tearDown() {
        checkingRepository.deleteAll();
        userRepository.deleteAll();
        accountHolderRepository.deleteAll();
        adminRepository.deleteAll();
    }

    @Test
    void setBalance_GoodBalance_Balance() {
        assertEquals(BigDecimal.valueOf(2000000).setScale(2), checking.getBalance().getAmount());
        checking.getBalance().setAmount(BigDecimal.valueOf(1244657));
        assertEquals("The balance has been set", checking.setBalance(checking.getBalance()));
    }
    @Test
    void setBalance_Balance_Penalty() {
        checking.getBalance().setAmount(BigDecimal.valueOf(150));
        assertEquals("A penalty has been taken from the balance", checking.setBalance(checking.getBalance()));
        assertEquals(BigDecimal.valueOf(110).setScale(2), checking.getBalance().getAmount());
    }

    @Test
    void getBalance_LastActualizedDateLess1month(){
        Money money3 = new Money(BigDecimal.valueOf(19000), Currency.getInstance("USD"));
        Checking checking3 = new Checking(money3, "fngmhg_fhª", user1, Date.valueOf("2022-06-23"));
        assertEquals(BigDecimal.valueOf(19000).setScale(2), checking3.getBalance().getAmount());
    }
    @Test
    void getBalance_LastActualizedDateMore1month(){
        Money money3 = new Money(BigDecimal.valueOf(19000), Currency.getInstance("USD"));
        Checking checking5 = new Checking(money3, "fngmhg_fhª", user1, Date.valueOf("2022-04-03"));
        assertEquals(BigDecimal.valueOf(18964).setScale(2), checking5.getBalance(checking5.getLastActualizedDate()).getAmount());
        assertEquals(Date.valueOf("2022-07-03"), checking5.getLastActualizedDate());
    }
}