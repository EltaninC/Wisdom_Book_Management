package com.example.wisdom_book_management.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Data
public class Appointment {
    private int appointment_id;
    private int user_id;
    private int book_id;
    private LocalDate appointment_date;
}
