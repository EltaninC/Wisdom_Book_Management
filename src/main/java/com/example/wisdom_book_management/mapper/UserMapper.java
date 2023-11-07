package com.example.wisdom_book_management.mapper;

import com.example.wisdom_book_management.domain.po.User;
import org.apache.ibatis.annotations.*;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface UserMapper
{
    @Select("Select * From user")
    List<User> GetUser();
    @Select("select * from user where user_id = #{id}")
    User GetUserById(int id);
    @Select("select count(*) from user where user_id = #{uid} and password = #{password}")
    int GetMatchCount(int uid,String password);

    @Insert("insert into user (user_name,password,email,phone,real_name,role_id,borrow_limit,borrowed_quantity,deposit)" +
            "VALUES (#{user.user_name},#{user.password},#{user.email},#{user.phone},#{user.real_name},#{user.role_id}," +
            "#{user.borrow_limit},#{user.borrowed_quantity},#{user.deposit})")
    void InsertUser(@Param("user") User user);

    @Delete("delete from user where user_id=#{id}")
    void DeleteUserById(int id);

    @Update("update user set user_name=#{user.user_name}, password=#{user.password}, email=#{user.email}, phone=#{user.phone}, " +
            "real_name=#{user.real_name}, role_id=#{user.role_id}, borrow_limit=#{user.borrow_limit}, " +
            "borrowed_quantity=#{user.borrowed_quantity}, deposit=#{user.deposit} where user_id=#{user.user_id}"
            )
    void UpdateUser(@Param("user") User user);

    @Update("update user set deposit=#{deposit}+deposit where user_id=#{user_id}"
    )
    void UpdateDeposit(BigDecimal deposit, int user_id);
}
