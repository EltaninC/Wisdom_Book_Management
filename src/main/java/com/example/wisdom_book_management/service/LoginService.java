package com.example.wisdom_book_management.service;

import com.example.wisdom_book_management.component.Result;

public interface LoginService {
    public Result login(int user_id, String password, String role);

    //判断账号是否存在
    public boolean hasMatchUser(int uid, String password);

    public boolean hasMatchAdmin(int uid, String password);

    //生成token
    public String GetToken(int user_id, String password, String role);
}
