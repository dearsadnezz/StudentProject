package com.example.cyberclub.Data;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Builder
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor(access= AccessLevel.PUBLIC, force=true)
public class Visit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Дата посещения не может быть пустой!")
    private String date;

    @Min(value = 1, message = "Время аренды не может быть мене 1 часа!")
    private int time;

    private float pay;

    @ManyToOne
    @NotNull(message = "Выберете компьютер!")
    private Computer computer;

    @ManyToOne
    @NotNull(message = "Выберете посетителя!")
    private Guest guest;
}
