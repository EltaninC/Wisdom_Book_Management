package com.example.wisdom_book_management.service;

import com.example.wisdom_book_management.component.Result;
import com.example.wisdom_book_management.domain.po.Refund;

public interface RefundService {

    public Result insertRefund(Refund refund);

    public Result<Refund> selectRefund();

    public Result<Refund> selectRefundById(int user_id);

    public Result<Refund> selectRefundByDay();
}
