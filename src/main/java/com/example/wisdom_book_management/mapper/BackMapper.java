package com.example.wisdom_book_management.mapper;

import com.example.wisdom_book_management.domain.po.Back;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface BackMapper {
    @Insert("insert into back (user_id,book_code,borrow_date,return_date,over_date)" +
            "VALUES(#{back.user_id},#{back.book_code},#{back.borrow_date},#{back.return_date},0)")
    void InsertBack(@Param("back") Back back);

    @Select("select * from back where user_id = #{user_id}")
    List<Back> GetBackByUid(int uid);

    @Select("select * from back")
    List<Back> GetBack();

    @Select("select Count(*) from back")
    int GetBackCount();

    @Select("select Count(*) from back where return_date like '${day}%'")
    int GetBackCountByDay(@Param("day") LocalDate day);
}
