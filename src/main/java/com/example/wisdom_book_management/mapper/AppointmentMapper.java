package com.example.wisdom_book_management.mapper;

import com.example.wisdom_book_management.domain.po.Appointment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AppointmentMapper {

    @Insert("Insert into appointment(book_id,user_id,appointment_date) Values(#{appointment.book_id}," +
            "#{appointment.user_id},#{appointment.appointment_date})")
    void InsertAppointment(@Param("appointment") Appointment appointment);

    @Select("Select * from appointment where user_id = #{user_id}")
    List<Appointment> SelectAppointmentByUserId(int user_id);
}
