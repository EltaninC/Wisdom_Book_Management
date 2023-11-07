package com.example.wisdom_book_management.service.impl;

import com.example.wisdom_book_management.component.Result;
import com.example.wisdom_book_management.domain.po.Shelf;
import com.example.wisdom_book_management.mapper.ShelfMapper;
import com.example.wisdom_book_management.service.ShelfService;
import com.example.wisdom_book_management.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.wisdom_book_management.component.ResultEnum.DATA_IS_NULL;
import static com.example.wisdom_book_management.component.ResultEnum.UNKNOWN_ERROR;

@Service
public class ShelfServiceImpl implements ShelfService {
    @Autowired
    ShelfMapper shelfMapper;
    @Override
    public Result selectShelf() {
        List<Shelf> shelf = shelfMapper.selectShelf();
        if(!shelf.isEmpty()){
            return ResultUtils.success(shelf);
        }
        return ResultUtils.Err(DATA_IS_NULL.getCode(), DATA_IS_NULL.getMsg());
    }

    @Override
    public Result insertShelf(Shelf shelf) {
        try {
            shelfMapper.insertShelf(shelf);
        }
        catch (Exception e){
            return ResultUtils.Err(UNKNOWN_ERROR.getCode(),UNKNOWN_ERROR.getMsg());
        }

        return ResultUtils.success();
    }

    @Override
    public Result updateShelf(Shelf shelf) {
        try {
            shelfMapper.updateShelf(shelf);
        }
        catch (Exception e){
            return ResultUtils.Err(UNKNOWN_ERROR.getCode(),UNKNOWN_ERROR.getMsg());
        }

        return ResultUtils.success();
    }

    @Override
    public Result deleteShelf(String shelf_code) {
        try {
            shelfMapper.deleteShelf(shelf_code);
        }
        catch (Exception e){
            return ResultUtils.Err(UNKNOWN_ERROR.getCode(),UNKNOWN_ERROR.getMsg());
        }

        return ResultUtils.success();
    }
}
