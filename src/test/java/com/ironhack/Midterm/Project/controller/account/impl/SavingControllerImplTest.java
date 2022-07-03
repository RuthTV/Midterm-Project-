package com.ironhack.Midterm.Project.controller.account.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.Midterm.Project.controller.account.dto.MoneyDTO;
import com.ironhack.Midterm.Project.controller.account.dto.SavingDTO;
import com.ironhack.Midterm.Project.model.money.Money;
import com.ironhack.Midterm.Project.model.accounts.Saving;
import com.ironhack.Midterm.Project.model.address.Address;
import com.ironhack.Midterm.Project.model.role.Role;
import com.ironhack.Midterm.Project.model.users.AccountHolder;
import com.ironhack.Midterm.Project.model.users.Admin;
import com.ironhack.Midterm.Project.repositories.accountRepository.SavingRepository;
import com.ironhack.Midterm.Project.repositories.userRepository.AccountHolderRepository;
import com.ironhack.Midterm.Project.repositories.userRepository.AdminRepository;
import com.ironhack.Midterm.Project.service.account.interfaces.SavingService;
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

import javax.transaction.Transactional;
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
class SavingControllerImplTest {
    @Autowired
    private SavingRepository savingRepository;
    @Autowired
    private SavingService savingService;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private AccountHolderRepository accountHolderRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private Admin admin;
    private AccountHolder accountHolder;
    private Saving saving, saving2;
    private Money money, money2;
    private Address address;
    private Role role;

    @BeforeEach
    void setUp() {
        address = new Address("Ambasaguas 55", 48891);
        admin = new Admin("Ruth", passwordEncoder.encode("123456"));
        role = new Role("ADMIN", admin);
        admin.setRoles(Set.of(role));
        money = new Money(BigDecimal.valueOf(2000000), Currency.getInstance("USD"));
        money2 = new Money(BigDecimal.valueOf(245000), Currency.getInstance("USD"));
        accountHolder = new AccountHolder("Julen Telleria", "dngmfhmf", Date.valueOf("1991-12-12"), address);
        saving = new Saving(money, "fngmhg_fhª", admin, Date.valueOf("2022-01-23"));
        saving2 = new Saving(money2, "fzhgnhª", accountHolder, Date.valueOf("2016-12-23"));
        adminRepository.save(admin);
        accountHolderRepository.save(accountHolder);
        savingRepository.saveAll(List.of(saving, saving2));
    }

    @AfterEach
    void tearDown() {
        savingRepository.deleteAll();
        accountHolderRepository.deleteAll();
        adminRepository.deleteAll();
    }

    @Test
    void findAll_NoParams_AllSavings() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Basic UnV0aDoxMjM0NTY=");
        MvcResult mvcResult = mockMvc.perform(get("/savings").headers(httpHeaders))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("2022-01-23"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("2016-12-23"));
    }

    @Test
    void findById_Id_Saving() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Basic UnV0aDoxMjM0NTY=");
        MvcResult mvcResult = mockMvc.perform(get("/savings/id/"+saving.getId()).headers(httpHeaders))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("2022-01-23"));
        assertFalse(mvcResult.getResponse().getContentAsString().contains("Ruth Telleria"));
    }
    @Test
    void findByUser_User_Saving() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Basic UnV0aDoxMjM0NTY=");
        MvcResult mvcResult = mockMvc.perform(get("/savings/primaryOwner/"+saving2.getPrimaryOwner().getId()).headers(httpHeaders))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("245000"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("2016-12-23"));
    }

    @Test
    void store() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Basic UnV0aDoxMjM0NTY=");
        BigDecimal money3 = BigDecimal.valueOf(298000);
        SavingDTO savingDto = new SavingDTO(money3, passwordEncoder.encode("123456ª"),accountHolder.getId(), Date.valueOf("2020-01-23"));
        String body = objectMapper.writeValueAsString(savingDto);
        MvcResult mvcResult = mockMvc.perform(
                        post("/savings").headers(httpHeaders)
                                .content(body)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("2020-01-23"));
        assertEquals(3, savingRepository.findAll().size());
    }
    @Test
    void update() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Basic UnV0aDoxMjM0NTY=");
        BigDecimal money3 = BigDecimal.valueOf(298000);
        SavingDTO savingDto = new SavingDTO(money3, passwordEncoder.encode("123456ª"),accountHolder.getId(), Date.valueOf("2020-01-23"));
        String body = objectMapper.writeValueAsString(savingDto);

        MvcResult mvcResult = mockMvc.perform(
                        put("/savings/" + saving.getId()).headers(httpHeaders)
                                .content(body)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNoContent())
                .andReturn();

        Optional<Saving> optionalSaving = savingRepository.findById(saving.getId());
        assertTrue(optionalSaving.isPresent());
        assertEquals("Ruth", optionalSaving.get().getPrimaryOwner().getUsername());
    }

    @Test
    @Transactional
    void updateBalance() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Basic UnV0aDoxMjM0NTY=");
        MoneyDTO moneyDto = new MoneyDTO(BigDecimal.valueOf(298000));
        String body = objectMapper.writeValueAsString(moneyDto);

        MvcResult mvcResult = mockMvc.perform(
                        patch("/savings/"+saving.getId()+"/balance").headers(httpHeaders)
                                .content(body)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNoContent())
                .andReturn();

        Optional<Saving> optionalSaving = savingRepository.findById(saving.getId());
        assertTrue(optionalSaving.isPresent());
        assertEquals(BigDecimal.valueOf(298000), optionalSaving.get().getBalance().getAmount());
    }

    @Test
    void delete_validId_SavingRemoved() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Basic UnV0aDoxMjM0NTY=");
        MvcResult mvcResult = mockMvc.perform(delete("/savings/" + saving.getId()).headers(httpHeaders))
                .andExpect(status().isNoContent())
                .andReturn();
        assertFalse(savingRepository.existsById(saving.getId()));
    }
}