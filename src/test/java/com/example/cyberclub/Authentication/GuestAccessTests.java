package com.example.cyberclub.Authentication;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
public class GuestAccessTests {
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
    }

    @Test
    @WithAnonymousUser
    void guestAccessToMainPage() throws Exception {
        mockMvc.perform(get("/")).andExpect(status().isOk());
    }

    @Test
    @WithAnonymousUser
    void guestAccessToLoginPage() throws Exception {
        mockMvc.perform(get("/Login")).andExpect(status().isOk());
    }

    @Test
    @WithAnonymousUser
    void guestAccessToRegistrationPage() throws Exception {
        mockMvc.perform(get("/Registration")).andExpect(status().isOk());
    }

    @Test
    @WithAnonymousUser
    void guestAccessToStatusPage() throws Exception {
        mockMvc.perform(get("/Status"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/Login"));
    }

    @Test
    @WithAnonymousUser
    void guestAccessToComputerPage() throws Exception {
        mockMvc.perform(get("/Computer"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/Login"));
    }

    @Test
    @WithAnonymousUser
    void guestAccessToGuestPage() throws Exception {
        mockMvc.perform(get("/Guest"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/Login"));
    }

    @Test
    @WithAnonymousUser
    void guestAccessToVisitPage() throws Exception {
        mockMvc.perform(get("/Visit"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/Login"));
    }
}
