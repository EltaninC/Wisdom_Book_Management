package com.example.wisdom_book_management.service;

import com.example.wisdom_book_management.component.Result;
import com.example.wisdom_book_management.domain.Book;

import java.util.List;

public interface BookService {
    public Result<Book> GetBooks();

    //更新图书信息
    public Result UpdateBook(Book book);

    //删除图书信息
    public Result DeleteBook(int bid);

    //添加书籍
    public Result InsertBook(Book book);

}
