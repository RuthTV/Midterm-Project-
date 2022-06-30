package com.ironhack.Midterm.Project.controller.account.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.Midterm.Project.model.accounts.Checking;
import com.ironhack.Midterm.Project.model.accounts.Money;
import com.ironhack.Midterm.Project.model.address.Address;
import com.ironhack.Midterm.Project.model.users.AccountHolder;
import com.ironhack.Midterm.Project.model.users.Admin;
import com.ironhack.Midterm.Project.repositories.accountRepository.CheckingRepository;
import com.ironhack.Midterm.Project.repositories.userRepository.AccountHolderRepository;
import com.ironhack.Midterm.Project.repositories.userRepository.AdminRepository;
import com.ironhack.Midterm.Project.service.account.interfaces.CheckingService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.net.URI;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
class CheckingControllerImplTest {
    @Autowired
    private CheckingRepository checkingRepository;
    @Autowired
    private CheckingService checkingService;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private AccountHolderRepository accountHolderRepository;
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private Admin admin;
    private AccountHolder accountHolder;
    private Checking checking1, checking2;
    private Money money, money2;
    private Address address;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        address = new Address("Ambasaguas 55", 48891);
        admin = new Admin("Ruth Telleria", "cbmnchmhc");
        money = new Money(BigDecimal.valueOf(2000000));
        money2 = new Money(BigDecimal.valueOf(245000));
        accountHolder = new AccountHolder("Julen Telleria", "dngmfhmf", Date.valueOf("1991-12-12"), address);
        checking1 = new Checking(money, "fngmhg_fhª", admin, Date.valueOf("2018-01-23"));
        checking2 = new Checking(money2, "fzhgnhª", accountHolder, Date.valueOf("2016-12-23"));
        adminRepository.save(admin);
        accountHolderRepository.save(accountHolder);
        checkingRepository.saveAll(List.of(checking1, checking2));
    }

    @AfterEach
    void tearDown() {
        checkingRepository.deleteAll();
        accountHolderRepository.deleteAll();
        adminRepository.deleteAll();
    }

    @Test
    void findAll_NoParams_AllCheckings() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/checkings"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("fngmhg_fhª"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Julen Telleria"));
    }

    @Test
    void findById_Id_Checking() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/checkings/id/"+checking1.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("2018-01-23"));
        assertFalse(mvcResult.getResponse().getContentAsString().contains("Ruth Telleria"));
    }

    @Test
    void findByUser_User_Checking() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/checkings/primaryOwner/"+checking2.getPrimaryOwner().getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("fzhgnhª"));
        assertFalse(mvcResult.getResponse().getContentAsString().contains("2016-12-23"));
    }

    @Test
    void store() throws Exception {
        Money money3 = new Money(BigDecimal.valueOf(298000));
        Admin admin3 = new Admin("Lorena Pardo", "ahaegjsg");
        Checking checking = new Checking(money3, "123456ª",admin3, Date.valueOf("2020-01-23"));
        String body = objectMapper.writeValueAsString(checking);
        MvcResult mvcResult = mockMvc.perform(
                        post("/checkings")
                                .content(body)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("Lorena Pardo"));
        assertTrue(checkingRepository.existsById(checking.getId()));
    }

    @Test
    void update() throws Exception {
        Money money3 = new Money(BigDecimal.valueOf(298000));
        Admin admin3 = new Admin("Lorena Pardo", "ahaegjsg");
        Checking checking = new Checking(money3, "123456ª",admin3, Date.valueOf("2020-01-23"));
        String body = objectMapper.writeValueAsString(checking);

        MvcResult mvcResult = mockMvc.perform(
                        put("/checkings/" + checking1.getId())
                                .content(body)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNoContent())
                .andReturn();

        Optional<Checking> optionalChecking = checkingRepository.findById(checking1.getId());
        assertTrue(optionalChecking.isPresent());
        assertEquals("Lorena Pardo", optionalChecking.get().getPrimaryOwner().getUsername());
    }

    @Test
    void updateBalance() throws Exception {
        Money money3 = new Money(BigDecimal.valueOf(298000));
        Checking checking = new Checking(money3, "fzhgnhª", accountHolder, Date.valueOf("2016-12-23"));
        String body = objectMapper.writeValueAsString(checking);

        MvcResult mvcResult = mockMvc.perform(
                        put("/checkings/" + checking2.getId())
                                .content(body)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNoContent())
                .andReturn();

        Optional<Checking> optionalChecking = checkingRepository.findById(checking2.getId());
        assertTrue(optionalChecking.isPresent());
        assertEquals(BigDecimal.valueOf(298000), optionalChecking.get().getBalance().getBalance());
    }

    @Test
    void delete_validId_CheckingRemoved() throws Exception {
        MvcResult mvcResult = mockMvc.perform(delete("/checkings/" + checking1.getId()))
                .andExpect(status().isNoContent())
                .andReturn();
        assertFalse(checkingRepository.existsById(checking1.getId()));
    }
}