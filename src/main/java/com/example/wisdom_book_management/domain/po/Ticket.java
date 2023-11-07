package com.example.wisdom_book_management.domain.po;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class Ticket {
    private int ticket_id;
    private int user_id;
    private String book_code;
    private String ticket_type;
    private String ticket_description;
    private double ticket_money;
}
