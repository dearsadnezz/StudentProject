package com.example.cyberclub.Data;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Builder
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor(access= AccessLevel.PUBLIC, force=true)
public class Guest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 3, message = "Фамилия не может содержать менее 3 символов!")
    private String surname;

    @Size(min = 2, message = "Имя не может содержать менее 2 символов!")
    private String name;

    @Size(min = 2, message = "Фамилия не может содержать менее 5 символов!")
    private String patronymic;

    @NotEmpty(message = "Номер паспорта не может быть пустым!")
    private String document;

    @NotEmpty(message = "Адрес не может быть пустым!")
    private String address;

    @NotEmpty(message = "Номер телефона не может быть пустым!")
    private String phone;

    private String getInitial(){return this.surname + " " + this.name + " " + this.patronymic;}
}
