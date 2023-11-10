package com.example.wisdom_book_management.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.wisdom_book_management.domain.po.Appointment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AppointmentMapper extends BaseMapper<Appointment> {

}
