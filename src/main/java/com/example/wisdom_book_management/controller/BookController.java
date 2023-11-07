package com.example.wisdom_book_management.controller;

import com.example.wisdom_book_management.component.Result;
import com.example.wisdom_book_management.domain.Book;
import com.example.wisdom_book_management.service.impl.BookServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    BookServiceImpl bookService;

    //返回全部书本信息
    @RequestMapping("/all")
    public Result<Book> Books(){
        return bookService.GetBooks();
    }

    /*
    以下方法，权限级别为管理员
    存在切面
    进行权限判断
    */

    //更新图书信息
    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    public Result UpdateBook(HttpServletRequest request, @RequestBody Book book){
        return bookService.UpdateBook(book);
    }

    //删除图书
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public Result DeleteBook(HttpServletRequest request, int book_id){
        return bookService.DeleteBook(book_id);
    }

    //添加书籍
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Result AddBook(HttpServletRequest request,@RequestBody Book book){
        return bookService.InsertBook(book);
    }

    //前端处理了
//    //通过书名查询书籍
//    @RequestMapping("/name")
//    public List<Book> SearchBooksByName(String book_name){
//        return bookService.GetBooksByName(book_name);
//    }
//    //书籍分类
//    @RequestMapping("/search_book_by_type")
//    public String SearchBooKsByType(HttpSession session, HttpServletRequest request){
//        session.setAttribute("bookList", bookService.GetBooksByType(request.getParameter("type_name")));
//        if(session.getAttribute("role").equals("s")){
//            return "book";
//        }
//        else{
//            return "book_management";
//        }
//    }
//
}
