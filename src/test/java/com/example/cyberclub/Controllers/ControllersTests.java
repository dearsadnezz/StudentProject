package com.example.cyberclub.Controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ControllersTests {
    @Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    @WithMockUser
    void mainController() throws Exception{
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("Main"))
                .andExpect(content().string(containsString("CyberPlay")));
    }

    @Test
    @WithMockUser
    void statusController() throws Exception{
        mockMvc.perform(get("/Status"))
                .andExpect(status().isOk())
                .andExpect(view().name("Status/Status"))
                .andExpect(content().string(containsString("Статусы")));
    }

    @Test
    @WithMockUser
    void computerController() throws Exception{
        mockMvc.perform(get("/Computer"))
                .andExpect(status().isOk())
                .andExpect(view().name("Computer/Computer"))
                .andExpect(content().string(containsString("Компьютеры/Консоли")));
    }

    @Test
    @WithMockUser
    void guestController() throws Exception{
        mockMvc.perform(get("/Guest"))
                .andExpect(status().isOk())
                .andExpect(view().name("Guest/Guest"))
                .andExpect(content().string(containsString("Посетители")));
    }

    @Test
    @WithMockUser
    void visitController() throws Exception{
        mockMvc.perform(get("/Visit"))
                .andExpect(status().isOk())
                .andExpect(view().name("Visit/Visit"))
                .andExpect(content().string(containsString("Учет аренды")));
    }

    @Test
    @WithAnonymousUser
    void registrationController() throws Exception{
        mockMvc.perform(get("/Registration"))
                .andExpect(status().isOk())
                .andExpect(view().name("Registration"))
                .andExpect(content().string(containsString("Регистрация")));
    }

    @Test
    @WithAnonymousUser
    void loginController() throws Exception{
        mockMvc.perform(get("/Login"))
                .andExpect(status().isOk())
                .andExpect(view().name("Login"))
                .andExpect(content().string(containsString("Вход")));
    }
}

