package com.example.wisdom_book_management.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdminMapper {
    @Select("select count(*) from Admin where admin_id = #{id} and pass_word = #{password}")
    public int GetCountAdmin(int id, String password);
}
