package com.example.wisdom_book_management.mapper;

import com.example.wisdom_book_management.domain.CascadeType;
import com.example.wisdom_book_management.domain.Type;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TypeMapper {
    @Select("select * from book_type Natural join parent_type;")
    public List<Type> SelectAllType();

    //一对多查询父类型
    @Select("select * from parent_type")
    @Results({
            @Result(property = "label", column = "parent_name"),
            @Result(property = "value", column = "parent_id"),
            @Result(property = "children", column = "parent_id",
                   javaType = List.class,
                   many = @Many(select = "com.example.wisdom_book_management.mapper.TypeMapper.selectTypeByParent") )
    })
    public List<CascadeType> getCascadeType();

    @Select("select type_name, type_id from book_type where parent_id = #{pid}")
    @Results({
            @Result(property = "label", column = "type_name"),
            @Result(property = "value", column = "type_id"),
    })
    public CascadeType selectTypeByParent(int pid);

    @Select("select type_name from book_type where type_id = #{tid}")
    public String SelectTypeName(int tid);

    @Select("select type_id from book_type where type_name = #{type_name}")
    public int SelectTypeId(String type_name);

    //新建子类别
    @Insert("insert book_type(type_id, type_name, parent_id) value(#{type.type_id},#{type.type_name},#{type.parent_id}) ")
    void InsertType(@Param("type") Type type);

    //更新子类别
    @Update("update book_type set type_name = #{type.type_name}, parent_id = #{type.parent_id} " +
            " where type_id = #{type.type_id}")
    void UpdateType(@Param("type") Type type);

    //删除子类别
    @Delete("delete from book_type where type_id = #{type_id}")
    void DeleteType(int type_id);

}
