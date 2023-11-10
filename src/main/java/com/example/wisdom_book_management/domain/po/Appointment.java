package com.example.wisdom_book_management.domain.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Data
@TableName("appointment")
public class Appointment {
    @TableId
    private int appointment_id;
    private int user_id;
    private int book_id;
    private LocalDate start_date;
    private LocalDate end_date;
    private String state;
}
