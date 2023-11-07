package com.example.wisdom_book_management.controller;

import com.example.wisdom_book_management.component.Result;
import com.example.wisdom_book_management.domain.dto.CascadeType;
import com.example.wisdom_book_management.domain.po.Type;
import com.example.wisdom_book_management.service.impl.TypeServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/type")
public class TypeController {
    @Autowired
    TypeServiceImpl typeService;

    //获取类别数据
    @RequestMapping("/all")
    public Result<Type> SelectAllType(){
        return typeService.SelectAllType();
    }

    //获取级联选择数据
    @RequestMapping("/cascadeType")
    public List<CascadeType> getCascadeType(){
        return typeService.getCascadeType();
    }

    /*
    以下方法，权限级别为管理员
    存在切面
    进行权限判断
    */

    //新建子类别
    @RequestMapping(value = "/addSon",method = RequestMethod.POST)
    public Result InsertType(HttpServletRequest request, @RequestBody Type type){
        return typeService.InsertType(type);
    }

    //更新子类别
    @RequestMapping(value = "/updateSon",method = RequestMethod.PUT)
    public Result UpdateType(HttpServletRequest request, @RequestBody Type type){
        return typeService.UpdateType(type);
    }

    //删除子类别
    @RequestMapping(value = "/deleteSon",method = RequestMethod.DELETE)
    public Result DeleteType(HttpServletRequest request, int type_id){
        return typeService.DeleteType(type_id);
    }
}
