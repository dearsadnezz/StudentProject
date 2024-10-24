package com.example.cyberclub.Models;

import com.example.cyberclub.Data.Computer;
import com.example.cyberclub.Data.Guest;
import com.example.cyberclub.Data.Status;
import com.example.cyberclub.Data.Visit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class VisitModelTests {
    @InjectMocks
    private Visit visit;
    private Computer computer;
    private Guest guest;
    private Status status;

    @BeforeEach
    void setUp() {
        computer = Computer.builder()
                .id(1L)
                .name("Computer")
                .status(status)
                .plan(50)
                .description("Computer")
                .build();
        visit = Visit.builder()
                .id(1L)
                .computer(computer)
                .guest(guest)
                .date("2024-09-12")
                .time(3)
                .build();
        visit.setPay(visit.getTime() * computer.getPlan());
    }

    @Test
    void visitId() {
        assertEquals(1L, visit.getId());
    }

    @Test
    void visitComputer() {
        assertEquals(computer, visit.getComputer());
    }

    @Test
    void visitGuest() {
        assertEquals(guest, visit.getGuest());
    }

    @Test
    void visitDate() {
        assertEquals("2024-09-12", visit.getDate());
    }

    @Test
    void visitTime() {
        assertEquals(3, visit.getTime());
    }

    @Test
    void visitPay() {
        assertEquals(150, visit.getPay());
    }

}
