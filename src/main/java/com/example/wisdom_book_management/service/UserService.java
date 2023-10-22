package com.example.wisdom_book_management.service;

import com.example.wisdom_book_management.component.Result;
import com.example.wisdom_book_management.domain.User;

import java.util.List;

public interface UserService {
    public Result<List<User> > GetUser();

    public Result DeleteUser(int id);

    public Result InsertUser(User user);

    public Result UpdateUser(User user);

    Result GetUserById(int id);
}
