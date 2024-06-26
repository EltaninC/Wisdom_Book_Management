package com.example.wisdom_book_management.service.impl;

import com.example.wisdom_book_management.component.Result;
import com.example.wisdom_book_management.domain.po.Back;
import com.example.wisdom_book_management.mapper.BackMapper;
import com.example.wisdom_book_management.service.BackService;
import com.example.wisdom_book_management.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static com.example.wisdom_book_management.component.ResultEnum.DATA_IS_NULL;

@Service
public class BackServiceImpl implements BackService {
    @Autowired
    BackMapper backMapper;

    public Result<Back> GetBackByUid(int uid){
        //1.获取数据
        List<Back> backs = backMapper.GetBackByUid(uid);
        //2.返回结果
        if(!backs.isEmpty()){
            return ResultUtils.success(backs);
        }
        else{
            return ResultUtils.Err(DATA_IS_NULL.getCode(), DATA_IS_NULL.getMsg());
        }
    }

    //以下管理员方法，使用Aop进行权限判断

    public Result<Back> GetBack(){
        if(!backMapper.GetBack().isEmpty()){
            return ResultUtils.success(backMapper.GetBack());
        }
        else{
            return ResultUtils.Err(DATA_IS_NULL.getCode(), DATA_IS_NULL.getMsg());
        }
    }

    //总归还数
    public Result GetBackCount(){
        return ResultUtils.success(backMapper.GetBackCount());
    }

    //今日归还数
    public Result GetBackCountByDay(){
        return ResultUtils.success(backMapper.GetBackCountByDay(LocalDate.now()));
    }
}
