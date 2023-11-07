package com.example.wisdom_book_management.domain.po;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class User {
    private int user_id;
    private String user_name;
    private String password;
    private String email;
    private String phone;
    private int role_id;
    private String real_name;
    private int borrow_limit;
    private int borrowed_quantity;
    private double deposit;
}
