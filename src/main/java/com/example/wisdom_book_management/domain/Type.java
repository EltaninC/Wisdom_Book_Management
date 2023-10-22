package com.example.wisdom_book_management.domain;

import lombok.Data;

@Data
public class Type {
    private int parent_id;
    private int type_id;
    private String type_name;
    private String parent_name;
}
