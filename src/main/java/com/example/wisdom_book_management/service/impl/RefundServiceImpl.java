package com.example.wisdom_book_management.service.impl;

import com.example.wisdom_book_management.component.Result;
import com.example.wisdom_book_management.domain.Refund;
import com.example.wisdom_book_management.mapper.RefundMapper;
import com.example.wisdom_book_management.service.RefundService;
import com.example.wisdom_book_management.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static com.example.wisdom_book_management.component.ResultEnum.UNKNOWN_ERROR;
import static com.example.wisdom_book_management.component.ResultEnum.UNLAWFUL_ACT;

@Service
public class RefundServiceImpl implements RefundService {
    @Autowired
    RefundMapper refundMapper;

    @Override
    @Transactional
    public Result insertRefund(Refund refund) {
        //判断是否退款过了
        if(refundMapper.isRefund(refund.getRecharge_id())!=1){
            refund.setRefund_time(LocalDateTime.now());
            refundMapper.InsertRefund(refund);
            return ResultUtils.success();
        }
        return ResultUtils.Err(UNLAWFUL_ACT.getCode(),UNLAWFUL_ACT.getMsg());
    }

    @Override
    public Result<Refund> selectRefund() {
        try {
            return ResultUtils.success(refundMapper.SelectRefund());
        }
        catch (Exception e){
            return ResultUtils.Err(UNKNOWN_ERROR.getCode(),UNKNOWN_ERROR.getMsg());
        }
    }

    @Override
    public Result<Refund> selectRefundById(int user_id) {
        try {
            return ResultUtils.success(refundMapper.SelectRefundById(user_id));
        }
        catch (Exception e){
            return ResultUtils.Err(UNKNOWN_ERROR.getCode(),UNKNOWN_ERROR.getMsg());
        }
    }

    @Override
    public Result<Refund> selectRefundByDay() {
        try {
            return ResultUtils.success(refundMapper.SelectRefundByDay(LocalDate.now()));
        }
        catch (Exception e){
            return ResultUtils.Err(UNKNOWN_ERROR.getCode(),UNKNOWN_ERROR.getMsg());
        }
    }
}
