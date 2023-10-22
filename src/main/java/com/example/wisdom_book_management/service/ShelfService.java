package com.example.wisdom_book_management.service;

import com.example.wisdom_book_management.component.Result;
import com.example.wisdom_book_management.domain.Shelf;

public interface ShelfService {
    //查询货架信息
    public Result selectShelf();

    //新建货架信息
    public Result insertShelf(Shelf shelf);

    //更新货架信息
    public Result updateShelf(Shelf shelf);

    //删处书架
    public Result deleteShelf(String shelf_code);
}
