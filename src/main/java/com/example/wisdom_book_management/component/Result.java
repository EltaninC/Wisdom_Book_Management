package com.example.wisdom_book_management.component;

import lombok.Data;

@Data
public class Result<T> {
    private  Integer code;
    private String msg;
    private T data;

}
