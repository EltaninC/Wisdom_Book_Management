package com.example.wisdom_book_management.controller;

import com.example.wisdom_book_management.component.Result;
import com.example.wisdom_book_management.domain.Recharge;
import com.example.wisdom_book_management.domain.Refund;
import com.example.wisdom_book_management.service.RefundService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RefundController {
    @Autowired
    RefundService refundService;

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
}
