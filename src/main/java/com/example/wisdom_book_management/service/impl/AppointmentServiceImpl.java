package com.example.wisdom_book_management.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.wisdom_book_management.component.Result;
import com.example.wisdom_book_management.domain.po.Appointment;
import com.example.wisdom_book_management.mapper.AppointmentMapper;
import com.example.wisdom_book_management.service.AppointmentService;
import com.example.wisdom_book_management.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import static com.example.wisdom_book_management.component.ResultEnum.UNKNOWN_ERROR;

@Service
public class AppointmentServiceImpl extends ServiceImpl<AppointmentMapper, Appointment> implements AppointmentService{
    @Autowired
    AppointmentMapper appointmentMapper;

    public Result Booking(Appointment appointment){
        //1.预约数据设置
        //1.1.预约取书时间
        LocalDate date = LocalDate.now();
        appointment.setEnd_date(date.plusDays(1));
        //1.2.预约状态
        appointment.setState("待取书");
        //2.插入数据库
        if(save(appointment)){
            //3.1.返回成功
            return ResultUtils.success();
        }
        //3.2.返回错误
        return ResultUtils.Err(UNKNOWN_ERROR.getCode(),UNKNOWN_ERROR.getMsg());
    }

}
