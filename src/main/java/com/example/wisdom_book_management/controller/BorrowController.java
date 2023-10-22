package com.example.wisdom_book_management.controller;

import com.example.wisdom_book_management.component.Result;
import com.example.wisdom_book_management.domain.Borrow;
import com.example.wisdom_book_management.service.BorrowServiceImpl;
import com.example.wisdom_book_management.utils.ResultUtils;
import com.example.wisdom_book_management.utils.TokenUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.Map;

import static com.example.wisdom_book_management.component.ResultEnum.UNKNOWN_ERROR;

@RestController
@RequestMapping("/borrow")
public class BorrowController {
    @Autowired
    BorrowServiceImpl borrowService;

    //用户查询借阅记录
    @RequestMapping(value = "/recordById",method = RequestMethod.GET)
    public Result<Borrow> GetBorrowByUid(HttpServletRequest request){
        String token = request.getHeader("token");
        int uid = Integer.parseInt(TokenUtil.getUserId(token));
        return borrowService.GetBorrowByUid(uid);
    }

    //管理员查询借阅记录
    @RequestMapping("/all")
    public Result<Borrow> GetBorrow(HttpServletRequest request){
        return borrowService.GetBorrow();
    }

    //借阅图书
    @RequestMapping("/add")
    public Result InsertBorrow(HttpServletRequest request, Map<String, Object> map){
        //获取用户id
        String token = request.getHeader("token");
        int uid = Integer.parseInt(TokenUtil.getUserId(token));
        //新增借阅记录,将书总数减一
        return borrowService.InsertBorrow(request.getParameter("book_code"),uid);
    }

    //判断未完成
    //还书
    @RequestMapping("/delete")
    public Result ReturnBook(int borrow_id){
        try{
            borrowService.ReturnBook(borrow_id);
        }
        catch (Exception e){
            return ResultUtils.Err(UNKNOWN_ERROR.getCode(),UNKNOWN_ERROR.getMsg());
        }
        return ResultUtils.success();
    }

    //续借
    @RequestMapping(value = "/renew", method = RequestMethod.POST)
    public Result Renew(Integer borrow_id, HttpServletRequest httpServletRequest){
        System.out.println(httpServletRequest.getParameter("borrow_id"));
        System.out.println(borrow_id);
        return borrowService.Renew(borrow_id);
    }


    //统计今日借出的书本数量
    @RequestMapping(value = "/countAll", method = RequestMethod.GET)
    public int GetBorrowCount(){
        return borrowService.GetBorrowCount();

    }

    //统计用户借书的总数
    @RequestMapping(value = "/countByID", method = RequestMethod.GET)
    public int GetBorrowCountById(int user_id){
        return borrowService.GetBorrowCountById(user_id);
    }

    //统计总的借出书的总数
    @RequestMapping(value = "/countByDay", method = RequestMethod.GET)
    public int GetBorrowCountByDay(){
        return borrowService.GetBorrowCountByDay();
    }
}
