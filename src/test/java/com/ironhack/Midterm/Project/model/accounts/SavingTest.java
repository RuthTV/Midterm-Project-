package com.ironhack.Midterm.Project.model.accounts;

import com.ironhack.Midterm.Project.model.accounts.Money;
import com.ironhack.Midterm.Project.model.accounts.Saving;
import com.ironhack.Midterm.Project.model.address.Address;
import com.ironhack.Midterm.Project.model.users.AccountHolder;
import com.ironhack.Midterm.Project.model.users.Admin;
import com.ironhack.Midterm.Project.repositories.accountRepository.SavingRepository;
import com.ironhack.Midterm.Project.repositories.userRepository.AccountHolderRepository;
import com.ironhack.Midterm.Project.repositories.userRepository.AdminRepository;
import com.ironhack.Midterm.Project.repositories.userRepository.UserClassRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Currency;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class SavingTest {
    @Autowired
    private UserClassRepository userClassRepository;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private AccountHolderRepository accountHolderRepository;
    @Autowired
    private SavingRepository savingRepository;
    private Admin user1;
    private AccountHolder user2;
    private Address address;
    private Money money, money2;
    private Saving saving, saving1;

    @BeforeEach
    void setUp() {
        address = new Address("Ambasaguas 55", 48891);
        user1 = new Admin("Julen Telleria", "cbmnchmhc");
        money = new Money(BigDecimal.valueOf(2000000), Currency.getInstance("USD"));
        money2 = new Money(BigDecimal.valueOf(245000), Currency.getInstance("USD"));
        user2 = new AccountHolder("Julen Telleria", "dngmfhmf", Date.valueOf("1991-12-12"), address);
        saving = new Saving(money, "fngmhg_fhª", user1, Date.valueOf("2018-01-23"));
        saving1 = new Saving(money2, "fzhgnhª", user2, Date.valueOf("2016-12-23"));
        adminRepository.save(user1);
        accountHolderRepository.save(user2);
        savingRepository.saveAll(List.of(saving, saving1));
    }

    @AfterEach
    void tearDown() {
        savingRepository.deleteAll();
        userClassRepository.deleteAll();
        accountHolderRepository.deleteAll();
        adminRepository.deleteAll();
    }

    @Test
    void setBalance_GoodBalance_Balance() {
        assertEquals(BigDecimal.valueOf(2000000).setScale(2), saving.getBalance().getAmount());
        saving.getBalance().setAmount(BigDecimal.valueOf(1244657));
        assertEquals("The balance has been set", saving.setBalance(saving.getBalance()));
    }
    @Test
    void setBalance_Balance_Penalty() {
        saving.getBalance().setAmount(BigDecimal.valueOf(950));
        assertEquals("A penalty has been taken from the balance", saving.setBalance(saving.getBalance()));
        assertEquals(BigDecimal.valueOf(910).setScale(2), saving.getBalance().getAmount());
    }

    @Test
    void getBalance_LastActualizedDateLess1month(){
        Money money3 = new Money(BigDecimal.valueOf(19000), Currency.getInstance("USD"));
        Saving saving3 = new Saving(money3, "fngmhg_fhª", user1, Date.valueOf("2022-06-23"));
        assertEquals(BigDecimal.valueOf(19000).setScale(2), saving3.getBalance().getAmount());
    }
    @Test
    void getBalance_LastActualizedDateMore1month(){
        Money money3 = new Money(BigDecimal.valueOf(19000), Currency.getInstance("USD"));
        Saving saving5 = new Saving(money3, "fngmhg_fhª", user1, Date.valueOf("2021-04-03"));
        assertEquals(BigDecimal.valueOf(19047.50).setScale(2), saving5.getBalance(saving5.getLastActualizedDate()).getAmount());
        assertEquals(Date.valueOf("2022-04-03"), saving5.getLastActualizedDate());
    }
}