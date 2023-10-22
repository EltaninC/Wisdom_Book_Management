package com.example.wisdom_book_management.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
public class CascadeType {
    private String value;
    private String label;
    private List<CascadeType> children;
}
