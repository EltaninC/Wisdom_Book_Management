package com.example.wisdom_book_management.mapper;

import com.example.wisdom_book_management.domain.po.Refund;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface RefundMapper {
    @Insert("Insert Into refund(recharge_id, user_id, refund_time, money) " +
            "Values(#{refund.recharge_id},#{refund.user_id},#{refund.refund_time},#{refund.money})" )
    void InsertRefund(@Param("refund") Refund refund);

    @Select("Select * from refund")
    List<Refund> SelectRefund();

    @Select("Select * from refund where user_id = #{user_id}")
    List<Refund> SelectRefundById(int user_id);

    @Select("Select * from refund where refund_time like '${day}%")
    List<Refund> SelectRefundByDay(@Param("day") LocalDate day);

    @Select("Select Count(*) from refund where recharge_id = #{recharge_id}")
    int isRefund(int recharge_id);

}
