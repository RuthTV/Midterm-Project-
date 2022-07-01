package com.ironhack.Midterm.Project.controller.user.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.Midterm.Project.model.users.Admin;
import com.ironhack.Midterm.Project.repositories.userRepository.AdminRepository;
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

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class AdminControllerImplTest {
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private Admin admin1, admin2;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        admin1 = new Admin("Julen Telleria", "dngmfhmf");
        admin2 = new Admin("Ruth Telleria", "cbmnchmhc");
        adminRepository.saveAll(List.of(admin1, admin2));
    }

    @AfterEach
    void tearDown() {
        adminRepository.deleteAll();
    }

    @Test
    void findAll_NoParams_AllAdmin() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/admins"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Ruth Telleria"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Julen Telleria"));
    }

    @Test
    void findById_Id_Admin() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/admins/"+admin1.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("dngmfhmf"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Julen Telleria"));
    }

    @Test
    void store() throws Exception {
        Admin admin = new Admin("Lorena Pardo", "fgj5516");
        String body = objectMapper.writeValueAsString(admin);
        MvcResult mvcResult = mockMvc.perform(
                        post("/admins")
                                .content(body)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("Lorena Pardo"));
        assertEquals(3, adminRepository.findAll().size());
    }

    @Test
    void update() throws Exception {
        Admin admin = new Admin("Lorena Pardo", "fgj5516");
        String body = objectMapper.writeValueAsString(admin);
        MvcResult mvcResult = mockMvc.perform(
                        put("/admins/" + admin1.getId())
                                .content(body)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNoContent())
                .andReturn();

        Optional<Admin> optionalAdmin = adminRepository.findById(admin1.getId());
        assertTrue(optionalAdmin.isPresent());
        assertEquals("Lorena Pardo", optionalAdmin.get().getUsername());
    }

    @Test
    void delete_validId_Admin() throws Exception {
        MvcResult mvcResult = mockMvc.perform(delete("/admins/" + admin2.getId()))
                .andExpect(status().isNoContent())
                .andReturn();
        assertFalse(adminRepository.existsById(admin2.getId()));
    }
}