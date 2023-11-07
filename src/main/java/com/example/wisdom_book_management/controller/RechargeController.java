package com.example.wisdom_book_management.controller;


import com.example.wisdom_book_management.component.Result;
import com.example.wisdom_book_management.domain.Recharge;
import com.example.wisdom_book_management.service.impl.RechargeServiceImpl;
import com.example.wisdom_book_management.utils.ResultUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recharge")
public class RechargeController {
    @Autowired
    RechargeServiceImpl rechargeService;

    @RequestMapping("/add")
    public Result InsertRecharge(@RequestBody Recharge recharge){
        rechargeService.InsertRecharge(recharge);
        return ResultUtils.success();
    }

    @RequestMapping("/all")
    public Result<Recharge> SelectRechargeA(HttpServletRequest request){
        return rechargeService.SelectRecharge();
    }

    @RequestMapping("/byId")
    public Result<Recharge> SelectRechargeById(HttpServletRequest request, int user_id){
        return rechargeService.SelectRechargeById(user_id);
    }

    @RequestMapping("/byDay")
    public Result<Recharge> SelectRechargeByDayA(HttpServletRequest request){
        return rechargeService.SelectRechargeByDay();
    }
}
