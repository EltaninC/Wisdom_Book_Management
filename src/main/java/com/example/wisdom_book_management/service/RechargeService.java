package com.example.wisdom_book_management.service;

import com.example.wisdom_book_management.component.Result;
import com.example.wisdom_book_management.domain.po.Recharge;

public interface RechargeService {
    public Result InsertRecharge(Recharge recharge);

    public Result<Recharge> SelectRecharge();

    public Result<Recharge> SelectRechargeByDay();

    public Result<Recharge> SelectRechargeById(int user_id);
}
