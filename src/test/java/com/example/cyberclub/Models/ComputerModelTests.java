package com.example.cyberclub.Models;

import com.example.cyberclub.Data.Computer;
import com.example.cyberclub.Data.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ComputerModelTests {
    @InjectMocks
    private Computer computer;
    private Status status;

    @BeforeEach
    void setUp() {
        computer = Computer.builder()
                .id(1L)
                .name("BasePlanComputer")
                .status(status)
                .plan(50)
                .description("Базовый компьютер в зале на 20 мест")
                .build();
    }

    @Test
    void computerId(){
        assertEquals(1L, computer.getId());
    }

    @Test
    void computerName(){
        assertEquals("BasePlanComputer", computer.getName());
    }

    @Test
    void computerStatus(){
        assertEquals(status, computer.getStatus());
    }

    @Test
    void computerPlan(){
        assertEquals(50, computer.getPlan());
    }

    @Test
    void computerDescription(){
        assertEquals("Базовый компьютер в зале на 20 мест", computer.getDescription());
    }
}
