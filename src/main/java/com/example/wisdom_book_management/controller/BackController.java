package com.example.wisdom_book_management.controller;

import com.example.wisdom_book_management.component.Result;
import com.example.wisdom_book_management.domain.po.Back;
import com.example.wisdom_book_management.service.BackService;
import com.example.wisdom_book_management.utils.TokenUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/back")
public class BackController {
    @Resource
    BackService backService;

    //通过userId查询归还记录
    @RequestMapping(value = "/RecordById", method = RequestMethod.GET)
    public Result<Back> GetBackByUid(HttpServletRequest request){
        String token = request.getHeader("token");
        int uid = Integer.parseInt(TokenUtil.getUserId(token));
        return backService.GetBackByUid(uid);
    }

    //管理员查询借阅记录
    @RequestMapping("/all")
    public Result<Back> GetBack(HttpServletRequest request){
        return backService.GetBack();
    }

    //总归还数
    @RequestMapping(value = "/countAll", method = RequestMethod.GET)
    public Result GetBackCount(){
        return backService.GetBackCount();
    }

    //今日归还数
    @RequestMapping(value = "/countByDay", method = RequestMethod.GET)
    public Result GetBackCountByDay(){
        return backService.GetBackCountByDay();
    }

}
