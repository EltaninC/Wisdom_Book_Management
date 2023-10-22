package com.example.wisdom_book_management.mapper;

import com.example.wisdom_book_management.domain.Recharge;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface RechargeMapper {
    @Insert("Insert Into recharge(user_id, recharge_time, money) " +
            "Values(#{recharge.user_id},#{recharge.recharge_time},#{recharge.money})" )
    void InsertRecharge(@Param("recharge") Recharge recharge);

    @Select("Select * from Recharge")
    List<Recharge> SelectRecharge();

    @Select("Select * from Recharge where user_id = #{user_id}")
    List<Recharge> SelectRechargeById(int user_id);

    @Select("Select * from Recharge where recharge_time like '${day}%'")
    List<Recharge> SelectRechargeByDay(@Param("day") LocalDate day);
}
