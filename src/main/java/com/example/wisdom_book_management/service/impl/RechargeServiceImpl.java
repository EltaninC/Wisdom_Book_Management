package com.example.wisdom_book_management.service.impl;

import com.example.wisdom_book_management.component.Result;
import com.example.wisdom_book_management.domain.Recharge;
import com.example.wisdom_book_management.mapper.RechargeMapper;
import com.example.wisdom_book_management.mapper.UserMapper;
import com.example.wisdom_book_management.service.RechargeService;
import com.example.wisdom_book_management.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static com.example.wisdom_book_management.component.ResultEnum.UNKNOWN_ERROR;

@Service
public class RechargeServiceImpl implements RechargeService {
    @Autowired
    RechargeMapper rechargeMapper;

    @Autowired
    UserMapper userMapper;

    @Transactional
    public Result InsertRecharge(Recharge recharge){
        try {
            recharge.setRecharge_time(LocalDateTime.now());
            rechargeMapper.InsertRecharge(recharge);
            userMapper.UpdateDeposit(recharge.getMoney(), recharge.getUser_id());
            return ResultUtils.success();
        }
        catch (Exception e){
            return ResultUtils.Err(UNKNOWN_ERROR.getCode(), UNKNOWN_ERROR.getMsg());
        }
    }

    public Result<Recharge> SelectRecharge(){
        try {
            return ResultUtils.success(rechargeMapper.SelectRecharge());
        }
        catch (Exception e){
            return ResultUtils.Err(UNKNOWN_ERROR.getCode(),UNKNOWN_ERROR.getMsg());
        }
    }

    public Result<Recharge> SelectRechargeByDay(){
        try {
            return ResultUtils.success(rechargeMapper.SelectRechargeByDay(LocalDate.now()));
        }
        catch (Exception e){
            return ResultUtils.Err(UNKNOWN_ERROR.getCode(),UNKNOWN_ERROR.getMsg());
        }
    }

    @Override
    public Result<Recharge> SelectRechargeById(int user_id) {
        try {
            return ResultUtils.success(rechargeMapper.SelectRechargeById(user_id));
        }
        catch (Exception e){
            return ResultUtils.Err(UNKNOWN_ERROR.getCode(),UNKNOWN_ERROR.getMsg());
        }
    }
}
