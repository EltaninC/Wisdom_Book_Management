package com.example.wisdom_book_management.controller;

import com.example.wisdom_book_management.component.Result;
import com.example.wisdom_book_management.domain.po.User;
import com.example.wisdom_book_management.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/account")
public class UserController {
    @Autowired
    UserServiceImpl userService;

    @RequestMapping(value = "/byId", method = RequestMethod.GET)
    public Result<User> GetUserById(HttpServletRequest request, int user_id){
        return userService.GetUserById(user_id);
    }


    /*
    以下方法，权限级别为管理员
    存在切面
    进行权限判断
    */

    @RequestMapping("/all")
    public Result<List<User>> GetUser(HttpServletRequest request){
        return userService.GetUser();
    }

    //新建用户
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Result AddUser(@RequestBody User user, HttpServletRequest request) {
        return userService.InsertUser(user);
    }

    //删除用户
    @RequestMapping("/delete")
    public Result DeleteUser(HttpServletRequest request, int user_id){
        return userService.DeleteUser(user_id);
    }

    //更新用户信息
    @RequestMapping("/update")
    public Result UpdateUser(HttpServletRequest request, @RequestBody User user){
        return userService.UpdateUser(user);
    }
}
