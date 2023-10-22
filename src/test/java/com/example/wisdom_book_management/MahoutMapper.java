package com.example.wisdom_book_management;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MahoutMapper {
    @Select("select user_id, book_id, Count(*) as value from borrow NATURAL join a_book group by book_id, user_id;")
    List<Mahout> getData();

    @Select("select user_id, book_id, (count(*)+5)/2 as value from" +
            "(select user_id, book_id  from borrow NATURAL join a_book " +
            "union all select user_id, book_id  from back NATURAL join a_book) as b " +
            "group by book_id, user_id order by user_id ")
    List<Mahout> getData1();


    @Select("select user_id, book_id, value from machout")
    List<Mahout> getData2();
}
