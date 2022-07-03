package com.ironhack.Midterm.Project.controller.user.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.Midterm.Project.model.role.Role;
import com.ironhack.Midterm.Project.model.users.Admin;
import com.ironhack.Midterm.Project.model.users.ThirdParty;
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

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ThirdPartyControllerImplTest {
    @Autowired
    ThirdPartyRepository thirdPartyRepository;

    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private ThirdParty thirdParty1, thirdParty2;
    private Admin admin;
    private Role role;

    @BeforeEach
    void setUp() {
        admin = new Admin("Ruth", passwordEncoder.encode("123456"));
        role = new Role("ADMIN", admin);
        admin.setRoles(Set.of(role));
        adminRepository.save(admin);
        thirdParty1 = new ThirdParty("Julen Telleria", passwordEncoder.encode("dngmfhmf"));
        thirdParty2 = new ThirdParty("Ruth Telleria",  passwordEncoder.encode("cbmnchmhc"));
        thirdPartyRepository.saveAll(List.of(thirdParty1, thirdParty2));
    }

    @AfterEach
    void tearDown() {
        thirdPartyRepository.deleteAll();
        adminRepository.deleteAll();
    }

    @Test
    void findAll() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Basic UnV0aDoxMjM0NTY=");
        MvcResult mvcResult = mockMvc.perform(get("/thirdPartys").headers(httpHeaders))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Ruth Telleria"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Julen Telleria"));
    }

    @Test
    void findById() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Basic UnV0aDoxMjM0NTY=");
        MvcResult mvcResult = mockMvc.perform(get("/thirdPartys/"+thirdParty1.getId()).headers(httpHeaders))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Julen Telleria"));
    }

    @Test
    void store() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Basic UnV0aDoxMjM0NTY=");
        ThirdParty thirdParty = new ThirdParty("Lorena Pardo", "fgj5516");
        String body = objectMapper.writeValueAsString(thirdParty);
        MvcResult mvcResult = mockMvc.perform(
                        post("/thirdPartys").headers(httpHeaders)
                                .content(body)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("Lorena Pardo"));
        assertEquals(3, thirdPartyRepository.findAll().size());
    }

    @Test
    void update() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Basic UnV0aDoxMjM0NTY=");
        ThirdParty thirdParty = new ThirdParty("Lorena Pardo", "fgj5516");
        String body = objectMapper.writeValueAsString(thirdParty);
        MvcResult mvcResult = mockMvc.perform(
                        put("/thirdPartys/" + thirdParty1.getId()).headers(httpHeaders)
                                .content(body)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNoContent())
                .andReturn();

        Optional<ThirdParty> optionalThirdParty = thirdPartyRepository.findById(thirdParty1.getId());
        assertTrue(optionalThirdParty.isPresent());
        assertEquals("Lorena Pardo", optionalThirdParty.get().getName());
    }

    @Test
    void delete_Id_ThirdParty() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Basic UnV0aDoxMjM0NTY=");
        MvcResult mvcResult = mockMvc.perform(delete("/thirdPartys/"+thirdParty1.getId()).headers(httpHeaders))
                .andExpect(status().isNoContent())
                .andReturn();
        assertFalse(thirdPartyRepository.existsById(thirdParty1.getId()));
    }
}