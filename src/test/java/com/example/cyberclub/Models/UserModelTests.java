package com.example.cyberclub.Models;

import com.example.cyberclub.Data.Users;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserModelTests {

    @InjectMocks
    private Users user;

    @BeforeEach
    void setUp() {
        user = Users.builder()
                .id(1L)
                .username("admin")
                .password("admin")
                .email("admin@gmail.com")
                .build();
    }

    @Test
    void userId() {
        assertEquals(1L, user.getId());
    }

    @Test
    void userUsername() {
        assertEquals("admin", user.getUsername());
    }

    @Test
    void userPassword() {
        assertEquals("admin", user.getPassword());
    }

    @Test
    void userEmail() {
        assertEquals("admin@gmail.com", user.getEmail());
    }
}
