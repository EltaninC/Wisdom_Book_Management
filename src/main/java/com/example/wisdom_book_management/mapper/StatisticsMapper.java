package com.example.wisdom_book_management.mapper;

import com.example.wisdom_book_management.domain.Statistics;
import com.example.wisdom_book_management.domain.Mahout;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StatisticsMapper {
    @Select("select b1.type_name as name, b1.c+b2.c as value from " +
            "(select Count(*) as c, type_name from back NATURAL join a_book NATURAL join book NATURAL join book_type GROUP BY type_id) as b1 join" +
            "(select Count(*) as c, type_name from borrow NATURAL join a_book NATURAL join book NATURAL join book_type GROUP BY type_id) as b2 " +
            "where b1.type_name= b2.type_name")
    List<Statistics> getTypeProportion();

    @Select("select b1.book_name as name, b1.c+b2.c as value from " +
            "(select Count(*) as c, book_name from back NATURAL join a_book NATURAL join book GROUP BY book_id) as b1 join" +
            "(select Count(*) as c, book_name from borrow NATURAL join a_book NATURAL join book GROUP BY book_id) as b2 " +
            "where b1.book_name= b2.book_name order by value desc LIMIT 5")
    List<Statistics> getBookRanking();

    //获取分析用户兴趣数据
    @Select("select user_id, book_id, (count(*)+5)/2 as value from" +
            "(select user_id, book_id  from borrow NATURAL join a_book " +
            "union all select user_id, book_id  from back NATURAL join a_book) as b " +
            "group by book_id, user_id order by user_id ")
    List<Mahout> getUserBook();
}
