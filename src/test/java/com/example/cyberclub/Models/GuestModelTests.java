package com.example.cyberclub.Models;

import com.example.cyberclub.Data.Guest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class GuestModelTests {
    @InjectMocks
    private Guest guest;

    @BeforeEach
    void setUp() {
        guest = Guest.builder()
                .id(1L)
                .surname("Попов")
                .name("Владимир")
                .patronymic("Игоревич")
                .document("6024 003234")
                .phone("+79289607788")
                .address("Большая Садовая д69")
                .build();
    }

    @Test
    void guestId() {
        assertEquals(1L, guest.getId());
    }

    @Test
    void guestSurname() {
        assertEquals("Попов", guest.getSurname());}

    @Test
    void guestName() {
        assertEquals("Владимир", guest.getName());
    }

    @Test
    void guestPatronymic() {
        assertEquals("Игоревич", guest.getPatronymic());
    }

    @Test
    void guestDocument() {
        assertEquals("6024 003234", guest.getDocument());
    }

    @Test
    void guestPhone() {
        assertEquals("+79289607788", guest.getPhone());
    }

    @Test
    void guestAddress() {
        assertEquals("Большая Садовая д69", guest.getAddress());
    }
}
