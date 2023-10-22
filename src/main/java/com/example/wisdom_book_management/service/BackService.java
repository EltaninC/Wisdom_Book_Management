package com.example.wisdom_book_management.service;

import com.example.wisdom_book_management.component.Result;
import com.example.wisdom_book_management.domain.Back;

import java.util.List;

public interface BackService {
    public Result<Back> GetBackByUid(int uid);

    public Result<Back> GetBack();

    //总归还数
    public Result GetBackCount();

    //今日归还数
    public Result GetBackCountByDay();
}
