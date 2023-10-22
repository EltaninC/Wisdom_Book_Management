package com.example.wisdom_book_management.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class Statistics {
    private int value;

    private String name;
}
