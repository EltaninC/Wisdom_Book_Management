package com.example.wisdom_book_management.domain.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    private int book_id;
    private int type_id;
    private String book_name;
    private String writer;
    private String publication;
    //前后端日期格式处理
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate publication_date;
    private String language;
    private String single_book_state;
    private int total;
    private String shelf_code;

    //转换字符串为LocalDateTime
    public LocalDate StT(String strLocalTime){
        //1.具有转换功能的对象
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        //2.要转换的对象
        LocalDateTime time = LocalDateTime.now();
        //3.发动功能
        String localTime = df.format(time);
        System.out.println("LocalDateTime转成String类型的时间："+localTime);
        //3.LocalDate发动，将字符串转换成  df格式的LocalDateTime对象，的功能
        LocalDate LocalTime = LocalDate.parse(strLocalTime,df);
        return LocalTime;
    }
}
