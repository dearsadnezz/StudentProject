package com.example.cyberclub.Models;

import com.example.cyberclub.Data.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class StatusModelTests {
    @InjectMocks
    private Status status;

    @BeforeEach
    void setUp() {
        status = Status.builder()
                .id(1L)
                .computer_status("Исправен")
                .build();
    }

    @Test
    void statusId() {
        assertEquals(1L, status.getId());
    }

    @Test
    void statusComputer_status() {
        assertEquals("Исправен", status.getComputer_status());
    }
}
