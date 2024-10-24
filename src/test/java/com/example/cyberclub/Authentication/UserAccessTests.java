package com.example.cyberclub.Authentication;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class UserAccessTests {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
    }

    @Test
    @WithMockUser
    void userAccessToMainPage() throws Exception {
        mockMvc.perform(get("/")).andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void userAccessToLoginPage() throws Exception {
        mockMvc.perform(get("/Login")).andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser
    void userAccessToRegistrationPage() throws Exception {
        mockMvc.perform(get("/Registration")).andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser
    void userAccessToStatusPage() throws Exception {
        mockMvc.perform(get("/Status")).andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void userAccessToComputerPage() throws Exception {
        mockMvc.perform(get("/Computer")).andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void userAccessToGuestPage() throws Exception {
        mockMvc.perform(get("/Guest")).andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void userAccessToVisitPage() throws Exception {
        mockMvc.perform(get("/Visit")).andExpect(status().isOk());
    }
}
