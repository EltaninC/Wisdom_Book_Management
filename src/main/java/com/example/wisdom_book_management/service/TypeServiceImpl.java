package com.example.wisdom_book_management.service;

import com.example.wisdom_book_management.component.Result;
import com.example.wisdom_book_management.domain.CascadeType;
import com.example.wisdom_book_management.domain.Type;
import com.example.wisdom_book_management.mapper.TypeMapper;
import com.example.wisdom_book_management.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.wisdom_book_management.component.ResultEnum.DATA_IS_NULL;
import static com.example.wisdom_book_management.component.ResultEnum.UNKNOWN_ERROR;

@Service
public class TypeServiceImpl implements TypeService{
    @Autowired
    TypeMapper typeMapper;

    public Result<Type> SelectAllType(){
        if(!typeMapper.SelectAllType().isEmpty()){
            return ResultUtils.success(typeMapper.SelectAllType());
        }
        else{
            return ResultUtils.Err(DATA_IS_NULL.getCode(),DATA_IS_NULL.getMsg());
        }
    }

    //新建子类别
    public Result InsertType(Type type){
        try {
            typeMapper.InsertType(type);
        }
        catch (Exception e) {
            return ResultUtils.Err(UNKNOWN_ERROR.getCode(), UNKNOWN_ERROR.getMsg());
        }
        return ResultUtils.success();
    }

    //更新子类别
    public Result UpdateType(Type type){
        try {
            typeMapper.UpdateType(type);
        }
        catch (Exception e) {
            return ResultUtils.Err(UNKNOWN_ERROR.getCode(), UNKNOWN_ERROR.getMsg());
        }
        return ResultUtils.success();
    }

    //删除子类别
    public Result DeleteType(int type_id){
        try {
            typeMapper.DeleteType(type_id);
        }
        catch (Exception e) {
            return ResultUtils.Err(UNKNOWN_ERROR.getCode(), UNKNOWN_ERROR.getMsg());
        }
        return ResultUtils.success();
    }

    //获取父类数据
    public List<CascadeType> getCascadeType(){
        return typeMapper.getCascadeType();
    }

    //
//    public int SelectTypeId(String type_name){
//        return typeMapper.SelectTypeId(type_name);
//    }
}
