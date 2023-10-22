package com.example.wisdom_book_management.utils;

import com.example.wisdom_book_management.component.Result;
import com.example.wisdom_book_management.component.ResultEnum;

public class ResultUtils {
    /*成功，且返回体有数据*/
    public static Result success(Object object) {
        Result result = new Result();
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMsg(ResultEnum.SUCCESS.getMsg());
        result.setData(object);
        return result;
    }
    //成功，但返回体没数据
    public static  Result success(){
        return success(null);
    }
    //失败返回信息
    public static Result Err(Integer code,String msg){
        Result r = new Result();
        r.setCode(code);
        r.setMsg(msg);
        return r;
    }
}
