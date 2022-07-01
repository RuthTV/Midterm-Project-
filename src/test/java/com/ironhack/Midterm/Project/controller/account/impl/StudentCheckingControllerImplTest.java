package com.ironhack.Midterm.Project.controller.account.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.Midterm.Project.model.money.Money;
import com.ironhack.Midterm.Project.model.accounts.StudentChecking;
import com.ironhack.Midterm.Project.model.address.Address;
import com.ironhack.Midterm.Project.model.users.AccountHolder;
import com.ironhack.Midterm.Project.model.users.Admin;
import com.ironhack.Midterm.Project.repositories.accountRepository.StudentCheckingRepository;
import com.ironhack.Midterm.Project.repositories.userRepository.AccountHolderRepository;
import com.ironhack.Midterm.Project.repositories.userRepository.AdminRepository;
import com.ironhack.Midterm.Project.service.account.interfaces.StudentCheckingService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
class StudentCheckingControllerImplTest {
    @Autowired
    private StudentCheckingRepository studentCheckingRepository;
    @Autowired
    private StudentCheckingService studentCheckingService;
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
    private StudentChecking studentChecking1, studentChecking2;
    private Money money, money2;
    private Address address;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        address = new Address("Ambasaguas 55", 48891);
        admin = new Admin("Ruth Telleria", "cbmnchmhc");
        money = new Money(BigDecimal.valueOf(2000000), Currency.getInstance("USD"));
        money2 = new Money(BigDecimal.valueOf(245000), Currency.getInstance("USD"));
        accountHolder = new AccountHolder("Julen Telleria", "dngmfhmf", Date.valueOf("1991-12-12"), address);
        studentChecking1 = new StudentChecking(money, "fngmhg_fhª", admin, Date.valueOf("2018-01-23"));
        studentChecking2 = new StudentChecking(money2, "fzhgnhª", accountHolder, Date.valueOf("2016-12-23"));
        adminRepository.save(admin);
        accountHolderRepository.save(accountHolder);
        studentCheckingRepository.saveAll(List.of(studentChecking1, studentChecking2));
    }


    @AfterEach
    void tearDown() {
        studentCheckingRepository.deleteAll();
        accountHolderRepository.deleteAll();
        adminRepository.deleteAll();
    }

    @Test
    void findAll_NoParams_AllStudentChecking() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/studentCheckings"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("2018-01-23"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("2016-12-23"));
    }

    @Test
    void findById_Id_Saving() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/studentCheckings/id/"+studentChecking1.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("2018-01-23"));
        assertFalse(mvcResult.getResponse().getContentAsString().contains("Ruth Telleria"));
    }

    @Test
    void findByUser_User_Saving() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/studentCheckings/primaryOwner/"+studentChecking2.getPrimaryOwner().getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("245000"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("2016-12-23"));
    }
    @Test
    void store() throws Exception {
        Money money3 = new Money(BigDecimal.valueOf(298000), Currency.getInstance("USD"));
        Admin admin3 = new Admin("Lorena Pardo", "ahaegjsg");
        StudentChecking studentChecking = new StudentChecking(money3, "123456ª",admin3, Date.valueOf("2020-01-23"));
        String body = objectMapper.writeValueAsString(studentChecking);
        MvcResult mvcResult = mockMvc.perform(
                        post("/studentCheckings")
                                .content(body)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("Lorena Pardo"));
        assertEquals(3, studentCheckingRepository.findAll().size());
    }

    @Test
    void update() throws Exception {
        Money money3 = new Money(BigDecimal.valueOf(298000), Currency.getInstance("USD"));
        Admin admin3 = new Admin("Lorena Pardo", "ahaegjsg");
        StudentChecking studentChecking = new StudentChecking(money3, "123456ª",admin3, Date.valueOf("2020-01-23"));
        String body = objectMapper.writeValueAsString(studentChecking);

        MvcResult mvcResult = mockMvc.perform(
                        put("/studentCheckings/" + studentChecking1.getId())
                                .content(body)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNoContent())
                .andReturn();

        Optional<StudentChecking> optionalStudentChecking = studentCheckingRepository.findById(studentChecking1.getId());
        assertTrue(optionalStudentChecking.isPresent());
        assertEquals("Lorena Pardo", optionalStudentChecking.get().getPrimaryOwner().getUsername());
    }

    @Test
    void updateBalance() throws Exception {
        Money money3 = new Money(BigDecimal.valueOf(298000), Currency.getInstance("USD"));
        StudentChecking studentChecking = new StudentChecking(money3, "fzhgnhª", accountHolder, Date.valueOf("2016-12-23"));
        String body = objectMapper.writeValueAsString(studentChecking);

        MvcResult mvcResult = mockMvc.perform(
                        put("/studentCheckings/" + studentChecking2.getId())
                                .content(body)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNoContent())
                .andReturn();

        Optional<StudentChecking> optionalStudentChecking = studentCheckingRepository.findById(studentChecking1.getId());
        assertTrue(optionalStudentChecking.isPresent());
        assertEquals(BigDecimal.valueOf(298000), optionalStudentChecking.get().getBalance().getAmount());
    }

    @Test
    void delete_validId_StudentRemoved() throws Exception {
        MvcResult mvcResult = mockMvc.perform(delete("/studentCheckings/" + studentChecking1.getId()))
                .andExpect(status().isNoContent())
                .andReturn();
        assertFalse(studentCheckingRepository.existsById(studentChecking1.getId()));
    }
}