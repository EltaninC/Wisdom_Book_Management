package com.example.wisdom_book_management.domain.po;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class Shelf {
    private String shelf_code;

    private String location;
}
