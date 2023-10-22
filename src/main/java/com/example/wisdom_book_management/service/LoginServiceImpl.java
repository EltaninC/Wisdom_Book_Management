package com.example.wisdom_book_management.service;

import com.example.wisdom_book_management.component.Result;
import com.example.wisdom_book_management.mapper.AdminMapper;
import com.example.wisdom_book_management.mapper.UserMapper;
import com.example.wisdom_book_management.utils.ResultUtils;
import com.example.wisdom_book_management.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static com.example.wisdom_book_management.component.ResultEnum.USER_IS_EXISTS;
import static com.example.wisdom_book_management.component.ResultEnum.USER_NOT_EXIST;

@Service
public class LoginServiceImpl implements LoginService{
    @Autowired
    UserMapper userMapper;
    @Autowired
    AdminMapper adminMapper;

    //登录功能
    public Result login(int user_id, String password, String role){
        //角色判断，账号密码查询
        if (role.equals("a") && hasMatchAdmin(user_id, password)) {
            //获取token
            return ResultUtils.success(GetToken(user_id,password,role));
        }
        else if (role.equals("u") && hasMatchUser(user_id, password)) {
            return ResultUtils.success(GetToken(user_id,password,role));
        }
        else {
            return ResultUtils.Err(USER_IS_EXISTS.getCode(), USER_NOT_EXIST.getMsg());
        }
    }

    //判断账号是否存在
    public boolean hasMatchUser(int uid, String password){
        return userMapper.GetMatchCount(uid, password) == 1;
    }

    public boolean hasMatchAdmin(int uid, String password) {
        return adminMapper.GetCountAdmin(uid, password) == 1;
    }

    //生成token
    public String GetToken(int user_id, String password, String role){
        Map<String, String> payload = new HashMap<>();
        payload.put("user_id", String.valueOf(user_id));
        payload.put("password",password);
        payload.put("role",role);
        String token = TokenUtil.sign(payload);
        return token;
    }
}
