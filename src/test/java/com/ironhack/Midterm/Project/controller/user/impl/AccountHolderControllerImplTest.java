package com.ironhack.Midterm.Project.controller.user.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.Midterm.Project.model.accounts.Checking;
import com.ironhack.Midterm.Project.model.accounts.Money;
import com.ironhack.Midterm.Project.model.address.Address;
import com.ironhack.Midterm.Project.model.users.AccountHolder;
import com.ironhack.Midterm.Project.model.users.Admin;
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
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class AccountHolderControllerImplTest {
    @Autowired
    private AccountHolderRepository accountHolderRepository;
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private AccountHolder accountHolder1, accountHolder2;
    private Address address;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        address = new Address("Ambasaguas 55", 48891);
        accountHolder1 = new AccountHolder("Julen Telleria", "dngmfhmf", Date.valueOf("1991-12-12"), address);
        accountHolder2 = new AccountHolder("Ruth Telleria", "154816", Date.valueOf("1999-10-21"), address);
        accountHolderRepository.saveAll(List.of(accountHolder1, accountHolder2));
    }

    @AfterEach
    void tearDown() {
        accountHolderRepository.deleteAll();
    }

    @Test
    void findAll_NoParams_AllAccountHolder() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/accountHolders"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Ruth Telleria"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Julen Telleria"));
    }

    @Test
    void findById_Id_AccountHolder() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/accountHolders/"+accountHolder1.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Ambasaguas 55"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Julen Telleria"));
    }

    @Test
    void store() throws Exception {
        AccountHolder accountHolder = new AccountHolder("Lorena Pardo", "fgj5516", Date.valueOf("1994-10-21"), address);
        String body = objectMapper.writeValueAsString(accountHolder);
        MvcResult mvcResult = mockMvc.perform(
                        post("/accountHolders")
                                .content(body)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("Lorena Pardo"));
        assertEquals(3, accountHolderRepository.findAll().size());
    }

    @Test
    void update() throws Exception {
        AccountHolder accountHolder = new AccountHolder("Lorena Pardo", "fgj5516", Date.valueOf("1994-10-21"), address);
        String body = objectMapper.writeValueAsString(accountHolder);

        MvcResult mvcResult = mockMvc.perform(
                        put("/accountHolders/" + accountHolder1.getId())
                                .content(body)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNoContent())
                .andReturn();

        Optional<AccountHolder> optionalAccountHolder = accountHolderRepository.findById(accountHolder1.getId());
        assertTrue(optionalAccountHolder.isPresent());
        assertEquals("Lorena Pardo", optionalAccountHolder.get().getUsername());
    }

    @Test
    void delete_validId_AccountHolder() throws Exception {
        MvcResult mvcResult = mockMvc.perform(delete("/accountHolders/" + accountHolder1.getId()))
                .andExpect(status().isNoContent())
                .andReturn();
        assertFalse(accountHolderRepository.existsById(accountHolder1.getId()));
    }
}