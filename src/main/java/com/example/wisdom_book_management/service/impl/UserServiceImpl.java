package com.example.wisdom_book_management.service.impl;

import com.example.wisdom_book_management.component.Result;
import com.example.wisdom_book_management.domain.User;
import com.example.wisdom_book_management.mapper.UserMapper;
import com.example.wisdom_book_management.service.UserService;
import com.example.wisdom_book_management.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.wisdom_book_management.component.ResultEnum.UNKNOWN_ERROR;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    public Result GetUser(){
        return ResultUtils.success(userMapper.GetUser());
    }

    public Result DeleteUser(int id){
        try {
            userMapper.DeleteUserById(id);
        }
        catch (Exception e){
            return ResultUtils.Err(UNKNOWN_ERROR.getCode(),UNKNOWN_ERROR.getMsg());
        }
        return ResultUtils.success();
    }

    public Result InsertUser(User user){
        try {
            userMapper.InsertUser(user);
        }
        catch (Exception e){
            return ResultUtils.Err(UNKNOWN_ERROR.getCode(),UNKNOWN_ERROR.getMsg());
        }
        return ResultUtils.success();
    }

    public Result UpdateUser(User user){
        try {
            userMapper.UpdateUser(user);
        }
        catch (Exception e){
            return ResultUtils.Err(UNKNOWN_ERROR.getCode(),UNKNOWN_ERROR.getMsg());
        }
        return ResultUtils.success();
    }

    @Override
    public Result GetUserById(int id) {
        return ResultUtils.success(userMapper.GetUserById(id));
    }

    //    public User GetUserById(int id){
//        return userMapper.GetUserById(id);
//    }

}
