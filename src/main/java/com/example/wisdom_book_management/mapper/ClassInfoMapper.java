package com.example.wisdom_book_management.mapper;

import com.example.wisdom_book_management.domain.dto.ClassInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ClassInfoMapper {
    @Select("select * from class_info where class_id = #{id}")
    public ClassInfo GetCIById(int id);
}
