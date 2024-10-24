package com.example.cyberclub.Data;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Builder
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor(access= AccessLevel.PUBLIC, force=true)
public class Computer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min=2, message = "Имя не может содержать менее 2 символов!")
    private String name;

    @Min(value = 50, message = "Тариф за 1 час аренды не может быть менее 50 рублей")
    private int plan;

    @Size(min = 5, message = "Описание не может быть менее 5 символов!")
    private String description;

    @ManyToOne
    @NotNull(message = "Необходимо выбрать статус компьютера!")
    private Status status;
}
