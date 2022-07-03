package com.ironhack.Midterm.Project.controller.user.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.Midterm.Project.model.role.Role;
import com.ironhack.Midterm.Project.model.users.Admin;
import com.ironhack.Midterm.Project.repositories.userRepository.AdminRepository;
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

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AdminControllerImplTest {
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private Admin admin1, admin2;
    private Role role, role2;

    @BeforeEach
    void setUp() {
        admin1 = new Admin("Julen", passwordEncoder.encode("654321"));
        admin2 = new Admin("Ruth", passwordEncoder.encode("123456"));
        role = new Role("ADMIN", admin1);
        admin1.setRoles(Set.of(role));
        role2 = new Role("ADMIN", admin2);
        admin2.setRoles(Set.of(role2));
        adminRepository.saveAll(List.of(admin1, admin2));
    }

    @AfterEach
    void tearDown() {
        adminRepository.deleteAll();
    }

    @Test
    void findAll_NoParams_AllAdmin() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Basic UnV0aDoxMjM0NTY=");
        MvcResult mvcResult = mockMvc.perform(get("/admins").headers(httpHeaders))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Ruth"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Julen"));
    }

    @Test
    void findById_Id_Admin() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Basic UnV0aDoxMjM0NTY=");
        MvcResult mvcResult = mockMvc.perform(get("/admins/"+admin1.getId()).headers(httpHeaders))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("dngmfhmf"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Julen Telleria"));
    }

    @Test
    void store() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Basic UnV0aDoxMjM0NTY=");
        Admin admin = new Admin("Lorena Pardo", "fgj5516");
        String body = objectMapper.writeValueAsString(admin);
        MvcResult mvcResult = mockMvc.perform(
                        post("/admins").headers(httpHeaders)
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
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Basic UnV0aDoxMjM0NTY=");
        Admin admin = new Admin("Lorena Pardo", "fgj5516");
        String body = objectMapper.writeValueAsString(admin);
        MvcResult mvcResult = mockMvc.perform(
                        put("/admins/" + admin1.getId()).headers(httpHeaders)
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
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Basic UnV0aDoxMjM0NTY=");
        MvcResult mvcResult = mockMvc.perform(delete("/admins/" + admin2.getId()).headers(httpHeaders))
                .andExpect(status().isNoContent())
                .andReturn();
        assertFalse(adminRepository.existsById(admin2.getId()));
    }
}