package com.ironhack.Midterm.Project.controller.account.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.Midterm.Project.controller.account.dto.Transference;
import com.ironhack.Midterm.Project.model.accounts.Checking;
import com.ironhack.Midterm.Project.model.accounts.CreditCard;
import com.ironhack.Midterm.Project.model.money.Money;
import com.ironhack.Midterm.Project.model.accounts.StudentChecking;
import com.ironhack.Midterm.Project.model.address.Address;
import com.ironhack.Midterm.Project.model.role.Role;
import com.ironhack.Midterm.Project.model.users.AccountHolder;
import com.ironhack.Midterm.Project.model.users.Admin;
import com.ironhack.Midterm.Project.model.users.ThirdParty;
import com.ironhack.Midterm.Project.repositories.accountRepository.CheckingRepository;
import com.ironhack.Midterm.Project.repositories.accountRepository.StudentCheckingRepository;
import com.ironhack.Midterm.Project.repositories.userRepository.AccountHolderRepository;
import com.ironhack.Midterm.Project.repositories.userRepository.AdminRepository;
import com.ironhack.Midterm.Project.repositories.userRepository.ThirdPartyRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Currency;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AccountControllerImplTest {
    @Autowired
    private CheckingRepository checkingRepository;
    @Autowired
    private StudentCheckingRepository studentCheckingRepository;
    @Autowired
    private AccountHolderRepository accountHolderRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private ThirdPartyRepository thirdPartyRepository;
    private Admin admin;
    private Role role, role1;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private AccountHolder accountHolder1, accountHolder2;
    private Checking checking;
    private StudentChecking studentChecking;
    private Money money, money2;
    private Address address;
    private ThirdParty thirdParty;

    @BeforeEach
    void setUp() {
        thirdParty = new ThirdParty("Xabi","3456");
        admin = new Admin("Lorena", passwordEncoder.encode("3456"));
        role = new Role("ADMIN", admin);
        admin.setRoles(Set.of(role));
        address = new Address("Ambasaguas 55", 48891);
        money = new Money(BigDecimal.valueOf(2000000), Currency.getInstance("USD"));
        money2 = new Money(BigDecimal.valueOf(245000), Currency.getInstance("USD"));
        accountHolder1 = new AccountHolder("Julen", "1234", Date.valueOf("1991-12-12"), address);
        accountHolder2 = new AccountHolder("Ruth", passwordEncoder.encode("12"), Date.valueOf("1994-08-07"), address);
        role1 = new Role("USER", accountHolder2);
        accountHolder2.setRoles(Set.of(role1));
        checking = new Checking(money, "fngmhg_fhª", accountHolder1, Date.valueOf("2018-01-23"));
        studentChecking = new StudentChecking(money2, "fzhgnhª", accountHolder2, Date.valueOf("2016-12-23"));
        accountHolderRepository.saveAll(List.of(accountHolder1, accountHolder2));
        checkingRepository.save(checking);
        studentCheckingRepository.save(studentChecking);
        adminRepository.save(admin);

    }

    @AfterEach
    void tearDown() {
        studentCheckingRepository.deleteAll();
        checkingRepository.deleteAll();
        accountHolderRepository.deleteAll();
        adminRepository.deleteAll();
    }

    @Test
    void transferBalance_Balance() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Basic TG9yZW5hOjM0NTY=");
        BigDecimal money3 = BigDecimal.valueOf(500);
        BigDecimal bigDecimal2 = money2.getAmount().add(money3);
        Transference transference = new Transference(money3, checking.getPrimaryOwner().getUsername(), studentChecking.getId());

        String body = objectMapper.writeValueAsString(transference);
        MvcResult mvcResult = mockMvc.perform(
                        patch("/transference").headers(httpHeaders)
                                .content(body)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNoContent())
                .andReturn();

        Optional<Checking> optionalChecking = checkingRepository.findById(checking.getId());
        assertTrue(optionalChecking.isPresent());
        assertEquals(BigDecimal.valueOf(1999500), optionalChecking.get().getBalance().getAmount());
        Optional<StudentChecking> optionalStudentChecking = studentCheckingRepository.findById(studentChecking.getId());
        assertTrue(optionalStudentChecking.isPresent());
        assertEquals(BigDecimal.valueOf(245500), optionalStudentChecking.get().getBalance().getAmount());
    }

    @Test
    void findAllMyAccounts_NoParams_AllMyAccounts() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Basic UnV0aDoxMg==");
        MvcResult mvcResult = mockMvc.perform(get("/myAccounts").headers(httpHeaders))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("2016-12-23"));
    }
}