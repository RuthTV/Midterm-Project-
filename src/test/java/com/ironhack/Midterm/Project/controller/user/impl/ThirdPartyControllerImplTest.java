package com.ironhack.Midterm.Project.controller.user.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.Midterm.Project.model.users.ThirdParty;
import com.ironhack.Midterm.Project.repositories.userRepository.ThirdPartyRepository;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class ThirdPartyControllerImplTest {
    @Autowired
    ThirdPartyRepository thirdPartyRepository;

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private ThirdParty thirdParty1, thirdParty2;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        thirdParty1 = new ThirdParty("Julen Telleria", "dngmfhmf");
        thirdParty2 = new ThirdParty("Ruth Telleria", "cbmnchmhc");
        thirdPartyRepository.saveAll(List.of(thirdParty1, thirdParty2));
    }

    @AfterEach
    void tearDown() {
        thirdPartyRepository.deleteAll();
    }

    @Test
    void findAll() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/thirdPartys"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Ruth Telleria"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Julen Telleria"));
    }

    @Test
    void findById() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/thirdPartys/"+thirdParty1.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("dngmfhmf"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Julen Telleria"));
    }

    @Test
    void store() throws Exception {
        ThirdParty thirdParty = new ThirdParty("Lorena Pardo", "fgj5516");
        String body = objectMapper.writeValueAsString(thirdParty);
        MvcResult mvcResult = mockMvc.perform(
                        post("/thirdPartys")
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
        ThirdParty thirdParty = new ThirdParty("Lorena Pardo", "fgj5516");
        String body = objectMapper.writeValueAsString(thirdParty);
        MvcResult mvcResult = mockMvc.perform(
                        put("/thirdPartys/" + thirdParty1.getId())
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
        MvcResult mvcResult = mockMvc.perform(delete("/thirdPartys/"+thirdParty1.getId()))
                .andExpect(status().isNoContent())
                .andReturn();
        assertFalse(thirdPartyRepository.existsById(thirdParty1.getId()));
    }
}