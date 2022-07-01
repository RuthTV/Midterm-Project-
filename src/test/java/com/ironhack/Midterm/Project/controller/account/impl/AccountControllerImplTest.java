package com.ironhack.Midterm.Project.controller.account.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.Midterm.Project.controller.account.dto.Transference;
import com.ironhack.Midterm.Project.model.accounts.Checking;
import com.ironhack.Midterm.Project.model.money.Money;
import com.ironhack.Midterm.Project.model.accounts.StudentChecking;
import com.ironhack.Midterm.Project.model.address.Address;
import com.ironhack.Midterm.Project.model.users.AccountHolder;
import com.ironhack.Midterm.Project.repositories.accountRepository.CheckingRepository;
import com.ironhack.Midterm.Project.repositories.accountRepository.StudentCheckingRepository;
import com.ironhack.Midterm.Project.repositories.userRepository.AccountHolderRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Currency;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class AccountControllerImplTest {
    @Autowired
    private CheckingRepository checkingRepository;
    @Autowired
    private StudentCheckingRepository studentCheckingRepository;
    @Autowired
    private AccountHolderRepository accountHolderRepository;
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private AccountHolder accountHolder1, accountHolder2;
    private Checking checking;
    private StudentChecking studentChecking;
    private Money money, money2;
    private Address address;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        address = new Address("Ambasaguas 55", 48891);
        money = new Money(BigDecimal.valueOf(2000000), Currency.getInstance("USD"));
        money2 = new Money(BigDecimal.valueOf(245000), Currency.getInstance("USD"));
        accountHolder1 = new AccountHolder("Julen Telleria", "dngmfhmf", Date.valueOf("1991-12-12"), address);
        accountHolder2 = new AccountHolder("Ruth Telleria", "dzgnjg551", Date.valueOf("1994-08-07"), address);
        checking = new Checking(money, "fngmhg_fhª", accountHolder1, Date.valueOf("2018-01-23"));
        studentChecking = new StudentChecking(money2, "fzhgnhª", accountHolder2, Date.valueOf("2016-12-23"));
        accountHolderRepository.saveAll(List.of(accountHolder1, accountHolder2));
        checkingRepository.save(checking);
        studentCheckingRepository.save(studentChecking);
    }

    @AfterEach
    void tearDown() {
        studentCheckingRepository.deleteAll();
        checkingRepository.deleteAll();
        accountHolderRepository.deleteAll();
    }

    @Test
    void transferBalance_Balance() throws Exception {
        BigDecimal money3 = BigDecimal.valueOf(500);
        BigDecimal bigDecimal2 = money2.getAmount().add(money3);
        Transference transference = new Transference(money3, checking.getPrimaryOwner().getUsername(), studentChecking.getId());

        String body = objectMapper.writeValueAsString(List.of(transference));
        MvcResult mvcResult = mockMvc.perform(
                        put("/transference")
                                .content(body)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNoContent())
                .andReturn();

        Optional<Checking> optionalChecking = checkingRepository.findById(checking.getId());
        assertTrue(optionalChecking.isPresent());
        assertEquals(BigDecimal.valueOf(1999500), optionalChecking.get().getBalance().getAmount());
        Optional<StudentChecking> optionalStudentChecking = studentCheckingRepository.findById(studentChecking.getId());
        assertTrue(optionalChecking.isPresent());
        assertEquals(BigDecimal.valueOf(245500), optionalChecking.get().getBalance().getAmount());
    }
}