package com.example.wisdom_book_management.mapper;

import com.example.wisdom_book_management.domain.po.Shelf;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ShelfMapper {
    @Select("select * from shelf")
    List<Shelf> selectShelf();

    @Insert("insert shelf(shelf_code, location) value(#{s.shelf_code}, #{s.location})")
    void insertShelf(@Param("s") Shelf shelf);

    @Update("update shelf set location = #{s.location} where shelf_code=#{s.shelf_code}")
    void updateShelf(@Param("s") Shelf shelf);

    @Delete("delete from shelf where shelf_code=#{shelf_code}")
    void deleteShelf(String shelf_code);
}
