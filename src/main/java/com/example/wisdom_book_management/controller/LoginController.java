package com.example.wisdom_book_management.controller;

import com.example.wisdom_book_management.component.Result;
import com.example.wisdom_book_management.mapper.UserMapper;
import com.example.wisdom_book_management.service.impl.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.Map;

@RestController
public class LoginController {
    @Autowired
    UserMapper userMapper;
    @Autowired
    LoginServiceImpl loginService;

    @Autowired
    BookController bookController;

    @Autowired
    UserController userController;

    //登录
    @PostMapping("/login")
    public Result login(@RequestParam("user_id") int user_id, @RequestParam("password") String password,
                        @RequestParam("role") String role, Map<String, Object> map) {
        return loginService.login(user_id,password,role);
    }


}
