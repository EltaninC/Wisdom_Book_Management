package com.example.wisdom_book_management.service;

import com.example.wisdom_book_management.component.Result;
import com.example.wisdom_book_management.domain.dto.CascadeType;
import com.example.wisdom_book_management.domain.po.Type;

import java.util.List;

public interface TypeService {
    public Result<Type> SelectAllType();

    //新建子类别
    public Result InsertType(Type type);

    //更新子类别
    public Result UpdateType(Type type);

    //删除子类别
    public Result DeleteType(int type_id);

    //获取父类数据-包含子类
    public List<CascadeType> getCascadeType();
}
