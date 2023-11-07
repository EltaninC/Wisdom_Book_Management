package com.example.wisdom_book_management.service.impl;

import com.example.wisdom_book_management.component.Result;
import com.example.wisdom_book_management.domain.po.Book;
import com.example.wisdom_book_management.mapper.ABookMapper;
import com.example.wisdom_book_management.mapper.BookMapper;
import com.example.wisdom_book_management.service.BookService;
import com.example.wisdom_book_management.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.wisdom_book_management.component.ResultEnum.DATA_IS_NULL;
import static com.example.wisdom_book_management.component.ResultEnum.UNKNOWN_ERROR;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    BookMapper bookMapper;

    @Autowired
    TypeServiceImpl typeService;

    @Autowired
    ABookMapper aBookMapper;

    //获取所有图书
    public Result<Book> GetBooks(){
        if(!bookMapper.GetBook().isEmpty()){
            return ResultUtils.success(bookMapper.GetBook());
        }
        else{
            return ResultUtils.Err(DATA_IS_NULL.getCode(),DATA_IS_NULL.getMsg());
        }
    }

    //更新图书信息
    public Result UpdateBook(Book book){
        try {
            bookMapper.Update(book);
        }
        catch (Exception e) {
            return ResultUtils.Err(UNKNOWN_ERROR.getCode(), UNKNOWN_ERROR.getMsg());
        }
        return ResultUtils.success();
    }

    //删除图书信息
    @Transactional
    public Result DeleteBook(int bid){
        try {
            //删除书本条码信息
            aBookMapper.deleteABookById(bid);
            //删除书籍信息
            bookMapper.DeleteById(bid);
        }
        catch (Exception e) {
            return ResultUtils.Err(UNKNOWN_ERROR.getCode(), UNKNOWN_ERROR.getMsg());
        }
        return ResultUtils.success();
    }

    //添加书籍
    @Transactional
    public Result InsertBook(Book book){
        try{
            //新建书籍信息
            bookMapper.Insert(book);
            //生成书本条码，插入书本
            int book_id = book.getBook_id();
            int total = book.getTotal();
            for(int i = 1; i <= total; i++){
                aBookMapper.insertABook(book_id,String.format("%05d",book_id)+String.format("%03d",i));
            }
        }
        catch (Exception e) {
            return ResultUtils.Err(UNKNOWN_ERROR.getCode(), UNKNOWN_ERROR.getMsg());
        }
        return ResultUtils.success();
    }


//        前端处理了不需要了，按名字搜索的图书
//    //分类书籍
//    public List<Book> GetBooksByType(String typeName) {
//        int tid = typeService.SelectTypeId(typeName);
//        return bookMapper.GetBookByType(tid);
//    }
//    public List<Book> GetBooksByName(String name){
//        name = '%'+name+'%';
//        return bookMapper.GetBookByName(name);
//    }
}
