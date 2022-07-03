package com.ironhack.Midterm.Project.controller.account.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.Midterm.Project.controller.account.dto.CheckingDTO;
import com.ironhack.Midterm.Project.controller.account.dto.CreditCardDTO;
import com.ironhack.Midterm.Project.controller.account.dto.MoneyDTO;
import com.ironhack.Midterm.Project.model.accounts.CreditCard;
import com.ironhack.Midterm.Project.model.money.Money;
import com.ironhack.Midterm.Project.model.address.Address;
import com.ironhack.Midterm.Project.model.role.Role;
import com.ironhack.Midterm.Project.model.users.AccountHolder;
import com.ironhack.Midterm.Project.model.users.Admin;
import com.ironhack.Midterm.Project.repositories.accountRepository.CreditCardRepository;
import com.ironhack.Midterm.Project.repositories.userRepository.AccountHolderRepository;
import com.ironhack.Midterm.Project.repositories.userRepository.AdminRepository;
import com.ironhack.Midterm.Project.service.account.interfaces.CreditCardService;
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
class CreditCardControllerImplTest {
    @Autowired
    private CreditCardRepository creditCardRepository;
    @Autowired
    private CreditCardService creditCardService;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private AccountHolderRepository accountHolderRepository;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private PasswordEncoder passwordEncoder;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private Admin admin;
    private AccountHolder accountHolder;
    private CreditCard creditCard, creditCard1;
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
        accountHolder = new AccountHolder("Julen Telleria",passwordEncoder.encode( "123"), Date.valueOf("1991-12-12"), address);
        creditCard = new CreditCard(money, "fngmhg_fhª", admin, Date.valueOf("2018-01-23"));
        creditCard1 = new CreditCard(money2, "fzhgnhª", accountHolder, Date.valueOf("2022-07-03"));
        adminRepository.save(admin);
        accountHolderRepository.save(accountHolder);
        creditCardRepository.saveAll(List.of(creditCard, creditCard1));
    }

    @AfterEach
    void tearDown() {
        creditCardRepository.deleteAll();
        accountHolderRepository.deleteAll();
        adminRepository.deleteAll();
    }

    @Test
    void findAll_NoParams_AllCreditCards() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Basic UnV0aDoxMjM0NTY=");
        MvcResult mvcResult = mockMvc.perform(get("/creditCards").headers(httpHeaders))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("2022-07-03"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("2018-01-23"));
    }

    @Test
    void findById_Id_CreditCards() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Basic UnV0aDoxMjM0NTY=");
        MvcResult mvcResult = mockMvc.perform(get("/creditCards/id/"+creditCard.getId()).headers(httpHeaders))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("2018-01-23"));
        assertFalse(mvcResult.getResponse().getContentAsString().contains("Ruth Telleria"));
    }

    @Test
    void findByUser_User_Checking() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Basic UnV0aDoxMjM0NTY=");
        MvcResult mvcResult = mockMvc.perform(get("/creditCards/primaryOwner/"+creditCard1.getPrimaryOwner().getId()).headers(httpHeaders))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("2022-07-03"));
    }
    @Test
    void store() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Basic UnV0aDoxMjM0NTY=");
        BigDecimal money3 = BigDecimal.valueOf(298000);
        CreditCardDTO creditCardDTO = new CreditCardDTO(money3, "fzhgnhª", accountHolder.getId(), Date.valueOf("2016-12-23"));
        String body = objectMapper.writeValueAsString(creditCardDTO);
        MvcResult mvcResult = mockMvc.perform(
                        post("/creditCards").headers(httpHeaders)
                                .content(body)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("2016-12-23"));
        assertEquals(3, creditCardRepository.findAll().size());
    }

    @Test
    void update() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Basic UnV0aDoxMjM0NTY=");
        BigDecimal money3 = BigDecimal.valueOf(298000);
        CreditCardDTO creditCardDTO = new CreditCardDTO(money3, passwordEncoder.encode("fzhgnh"), accountHolder.getId(), Date.valueOf("2016-12-23"));
        String body = objectMapper.writeValueAsString(creditCardDTO);

        MvcResult mvcResult = mockMvc.perform(
                        put("/creditCards/" + creditCard.getId()).headers(httpHeaders)
                                .content(body)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNoContent())
                .andReturn();

        Optional<CreditCard> optionalCreditCard = creditCardRepository.findById(creditCard.getId());
        assertTrue(optionalCreditCard.isPresent());
        assertEquals("Ruth", optionalCreditCard.get().getPrimaryOwner().getUsername());
    }

    @Test
    @Transactional
    void updateBalance() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Basic UnV0aDoxMjM0NTY=");
        MoneyDTO moneyDto = new MoneyDTO(BigDecimal.valueOf(298000));
        String body = objectMapper.writeValueAsString(moneyDto);

        MvcResult mvcResult = mockMvc.perform(
                        patch("/creditCards/"+creditCard1.getId()+"/balance").headers(httpHeaders)
                                .content(body)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNoContent())
                .andReturn();

        Optional<CreditCard> optionalCreditCard = creditCardRepository.findById(creditCard1.getId());
        assertTrue(optionalCreditCard.isPresent());
        assertEquals(BigDecimal.valueOf(298000), optionalCreditCard.get().getBalance().getAmount());
    }
    @Test
    void delete_validId_CheckingRemoved() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Basic UnV0aDoxMjM0NTY=");
        MvcResult mvcResult = mockMvc.perform(delete("/creditCards/" + creditCard.getId()).headers(httpHeaders))
                .andExpect(status().isNoContent())
                .andReturn();
        assertFalse(creditCardRepository.existsById(creditCard.getId()));
    }
}