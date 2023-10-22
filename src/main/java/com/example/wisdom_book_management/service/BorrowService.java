package com.example.wisdom_book_management.service;

import com.example.wisdom_book_management.component.Result;
import com.example.wisdom_book_management.domain.Borrow;

import java.time.LocalDate;
import java.util.List;

public interface BorrowService {

    public Result<Borrow> GetBorrowByUid(Object uid);

    public Result<Borrow> GetBorrow();


    public Result InsertBorrow(String book_code, int user_id);

    public void ReturnBook(int id);

    //续借
    public Result Renew(int id);

    //统计总的借出的书本数量
    public int GetBorrowCount();

    //统计总的借出的书本数量
    public int GetBorrowCountById(int user_id);

    //统计今日借出书的总数
    public int GetBorrowCountByDay();
}
