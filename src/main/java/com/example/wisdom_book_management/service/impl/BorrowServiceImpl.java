package com.example.wisdom_book_management.service.impl;

import com.example.wisdom_book_management.component.Result;
import com.example.wisdom_book_management.domain.po.Back;
import com.example.wisdom_book_management.domain.po.Borrow;
import com.example.wisdom_book_management.domain.po.User;
import com.example.wisdom_book_management.mapper.*;
import com.example.wisdom_book_management.service.BorrowService;
import com.example.wisdom_book_management.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static com.example.wisdom_book_management.component.ResultEnum.*;


@Service
public class BorrowServiceImpl implements BorrowService {
    @Autowired
    BorrowMapper borrowMapper;
    @Autowired
    BackMapper backMapper;
    @Autowired
    ABookMapper aBookMapper;
    @Autowired
    BookMapper bookMapper;
    @Autowired
    UserMapper userMapper;

    //获取用户获取借阅记录
    public Result<Borrow> GetBorrowByUid(Object uid){
        //1.获取借阅记录
        List<Borrow> borrows = borrowMapper.GetBorrowByUid(uid);
        //2.返回结果
        if(!borrows.isEmpty()){
            return ResultUtils.success(borrows);
        }
        else{
            return ResultUtils.Err(DATA_IS_NULL.getCode(), DATA_IS_NULL.getMsg());
        }
    }

    //管理员获取借阅记录
    public Result<Borrow> GetBorrow(){
        if(!borrowMapper.GetBorrow().isEmpty()){
            return ResultUtils.success(borrowMapper.GetBorrow());
        }
        else{
            return ResultUtils.Err(DATA_IS_NULL.getCode(), DATA_IS_NULL.getMsg());
        }
    }

    //借书
    @Transactional
    public Result InsertBorrow(String book_code, int user_id){
        //获取用户信息
        User user = userMapper.GetUserById(user_id);
        //判断（该书是否正在借阅状态，条码是否正确，是否超过用户借书限制）
        if(aBookMapper.MatchBookCode(book_code)&&borrowMapper.GetBorrowByBookCode(book_code)==null
            &&borrowMapper.GetBorrowCountById(user_id)<user.getBorrowed_quantity()){
            //获取用户借阅时间限制
            int borrow_time = user.getBorrow_limit();
            Borrow borrow = new Borrow();
            borrow.setBook_code(book_code);
            borrow.setUser_id(user_id);
            LocalDateTime date = LocalDateTime.now();
            borrow.setBorrow_date(LocalDateTime.now());
            borrow.setExpect_return_date(date.plusDays(borrow_time));
            borrow.setRenew(0);
            //新增借阅记录
            borrowMapper.InsertBorrow(borrow);
            //总数减一
            bookMapper.BorrowBook(aBookMapper.GetBookId(book_code));
            return ResultUtils.success();
        }else{
            return ResultUtils.Err(UNKNOWN_ERROR.getCode(), UNKNOWN_ERROR.getMsg());
        }
    }

    //还书
    @Transactional
    public void ReturnBook(int id){
        Borrow borrow = borrowMapper.GetBorrowById(id);
        Back back = new Back();
        back.setBook_code(borrow.getBook_code());
        back.setUser_id(borrow.getUser_id());
        back.setBorrow_date(borrow.getBorrow_date());
        back.setReturn_date(LocalDateTime.now());
        //删除借阅记录
        borrowMapper.DeleteBorrow(id);
        //新增归还记录
        backMapper.InsertBack(back);
        //书总数加一
        bookMapper.ReturnBook(aBookMapper.GetBookId(back.getBook_code()));
    }

    //续借
    public Result Renew(int borrow_id){
        System.out.println(borrowMapper.isRenew(borrow_id));
        System.out.println(borrowMapper.isRenew(borrow_id)!=1);
        if(borrowMapper.isRenew(borrow_id)!=1){
            System.out.println(borrow_id);
            borrowMapper.Renew(borrow_id);
            return ResultUtils.success();
        }
        return ResultUtils.Err(UNKNOWN_ERROR.getCode(), USER_NOT_EXIST.getMsg());
    }

    //统计总的借出的书本数量
    public int GetBorrowCount(){
        return borrowMapper.GetBorrowCount();
    }

    //统计用户借书的总数
    @Override
    public int GetBorrowCountById(int user_id) {
        return borrowMapper.GetBorrowCountById(user_id);
    }

    //统计今日借出书的总数
    public int GetBorrowCountByDay(){
        return borrowMapper.GetBorrowCountByDay(LocalDate.now());
    }
}
