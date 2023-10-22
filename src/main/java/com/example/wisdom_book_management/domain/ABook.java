package com.example.wisdom_book_management.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class ABook {
    private int book_id;
    private String book_code;
}
