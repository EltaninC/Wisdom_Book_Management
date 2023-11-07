package com.example.wisdom_book_management.mapper;

import com.example.wisdom_book_management.domain.po.Book;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BookMapper {
    @Select("select * from book where book_id = #{bid}")
    public Book GetBookById(int bid);

    @Select("select * from book")
    public List<Book> GetBook();

    @Select("select * from book where book_name like #{name}")
    public List<Book> GetBookByName(String name);

    @Select("select * from book where type_id = #{tid}")
    List<Book> GetBookByType(int tid);

    @Delete("delete from book where book_id = #{bid}")
    public void DeleteById(int bid);

    @Update("Update book Set type_id = #{book.type_id}, book_name = #{book.book_name}, writer = #{book.writer}, " +
            "publication = #{book.publication}, publication_date = #{book.publication_date}, language = #{book.language}" +
            ",single_book_state = #{book.single_book_state} ,total = #{book.total}, shelf_code = #{book.shelf_code} " +
            "Where book_id = #{book.book_id}")
    public void Update(@Param("book")Book book);

    @Insert("Insert into book (book_id,type_id,book_name,writer,publication,publication_date,language,single_book_state," +
            "total,shelf_code)VALUES(#{book.book_id},#{book.type_id},#{book.book_name},#{book.writer},#{book.publication}," +
            "#{book.publication_date},#{book.language},#{book.single_book_state},#{book.total},#{book.shelf_code})")
    public void Insert(@Param("book")Book book);

    @Update("Update book Set total = total-1 Where book_id = #{book_id}")
    void BorrowBook(int book_id);

    @Update("Update book Set total = total+1 Where book_id = #{book_id}")
    void ReturnBook(int book_id);
}
