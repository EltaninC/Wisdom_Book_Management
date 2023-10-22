package com.example.wisdom_book_management.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ABookMapper {

    @Select("select Count(*) from a_book where book_code = #{book_code}")
    boolean MatchBookCode(String book_code);

    @Select("select book_id from a_book where book_code = #{book_code}")
    int GetBookId(String book_code);

    @Insert("insert into a_book(book_id, book_code) value(#{book_id}, #{book_code})")
    void insertABook(int book_id,String book_code);

    @Delete("delete from a_book where book_id=#{book_id}")
    void deleteABookById(int book_id);
}
