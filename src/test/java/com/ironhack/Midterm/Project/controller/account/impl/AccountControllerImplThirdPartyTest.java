package com.ironhack.Midterm.Project.controller.account.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.Midterm.Project.controller.user.dto.ThirdPartyDTO;
import com.ironhack.Midterm.Project.model.accounts.Checking;
import com.ironhack.Midterm.Project.model.accounts.Saving;
import com.ironhack.Midterm.Project.model.accounts.StudentChecking;
import com.ironhack.Midterm.Project.model.address.Address;
import com.ironhack.Midterm.Project.model.money.Money;
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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.util.Currency;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class AccountControllerImplThirdPartyTest {

    @Autowired
    private CheckingRepository checkingRepository;
    @Autowired
    private AccountHolderRepository accountHolderRepository;

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @Autowired
    private ThirdPartyRepository thirdPartyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private AccountHolder accountHolder1;
    private Checking checking;

    private Money money;
    private Address address;
    private ThirdParty thirdParty;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        thirdParty = new ThirdParty("Xabi","3456");
        address = new Address("Ambasaguas 55", 48891);
        money = new Money(BigDecimal.valueOf(2000000), Currency.getInstance("USD"));
        accountHolder1 = new AccountHolder("Julen", "1234", Date.valueOf("1991-12-12"), address);
        checking = new Checking(money, passwordEncoder.encode("fngmhg_fhª"), accountHolder1, Date.valueOf("2018-01-23"));
        thirdPartyRepository.save(thirdParty);
        accountHolderRepository.save(accountHolder1);
        checkingRepository.save(checking);
    }

    @AfterEach
    void tearDown() {
        thirdPartyRepository.deleteAll();
        checkingRepository.deleteAll();
        accountHolderRepository.deleteAll();
    }

    @Test
    void thirdPartyReceive() throws Exception {
        ThirdPartyDTO thirdPartyDTO = new ThirdPartyDTO(BigDecimal.valueOf(590), checking.getId(), checking.getSecretKey());
        String body = objectMapper.writeValueAsString(thirdPartyDTO);

        MvcResult mvcResult = mockMvc.perform(
                        patch("/thirdParty/"+ thirdParty.getHashedKey()+"/receive")
                                .content(body)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isAccepted())
                .andReturn();
        Optional<Checking> optionalChecking = checkingRepository.findById(checking.getId());
        assertTrue(optionalChecking.isPresent());
        assertEquals(BigDecimal.valueOf(2000590), optionalChecking.get().getBalance().getAmount());
    }

    @Test
    void thirdPartySend() throws Exception {
        ThirdPartyDTO thirdPartyDTO = new ThirdPartyDTO(BigDecimal.valueOf(590), checking.getId(), checking.getSecretKey());
        String body = objectMapper.writeValueAsString(thirdPartyDTO);

        MvcResult mvcResult = mockMvc.perform(
                        patch("/thirdParty/"+ thirdParty.getHashedKey()+"/send")
                                .content(body)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isAccepted())
                .andReturn();
        Optional<Checking> optionalChecking = checkingRepository.findById(checking.getId());
        assertTrue(optionalChecking.isPresent());
        assertEquals(BigDecimal.valueOf(1999410), optionalChecking.get().getBalance().getAmount());
    }
}
