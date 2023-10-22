package com.example.wisdom_book_management.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class ClassInfo {
    private int class_id;
    private String class_name;
}
