package com.example.wisdom_book_management.controller;

import com.example.wisdom_book_management.component.Result;
import com.example.wisdom_book_management.domain.po.Recharge;
import com.example.wisdom_book_management.domain.po.Refund;
import com.example.wisdom_book_management.service.RefundService;
import com.example.wisdom_book_management.service.impl.RechargeServiceImpl;
import com.example.wisdom_book_management.utils.ResultUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PropertyController {
    @Autowired
    RefundService refundService;
    @Autowired
    RechargeServiceImpl rechargeService;

    @RequestMapping(value = "/refund", method = RequestMethod.POST)
    public Result insertRefundA(HttpServletRequest request, @RequestBody Refund refund){
        return refundService.insertRefund(refund);
    }

    @RequestMapping("/refund-all")
    public Result<Refund> SelectRefundA(HttpServletRequest request){
        return refundService.selectRefund();
    }

    @RequestMapping("/refund-byId")
    public Result<Refund> SelectRefundById(HttpServletRequest request, int user_id){
        return refundService.selectRefundById(user_id);
    }


    @RequestMapping("/recharge/add")
    public Result InsertRecharge(@RequestBody Recharge recharge){
        rechargeService.InsertRecharge(recharge);
        return ResultUtils.success();
    }

    @RequestMapping("/recharge/all")
    public Result<Recharge> SelectRechargeA(HttpServletRequest request){
        return rechargeService.SelectRecharge();
    }

    @RequestMapping("/recharge/byId")
    public Result<Recharge> SelectRechargeById(HttpServletRequest request, int user_id){
        return rechargeService.SelectRechargeById(user_id);
    }

    @RequestMapping("/recharge/byDay")
    public Result<Recharge> SelectRechargeByDayA(HttpServletRequest request){
        return rechargeService.SelectRechargeByDay();
    }
}
