package com.example.wisdom_book_management.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.wisdom_book_management.domain.po.Appointment;
import com.example.wisdom_book_management.mapper.AppointmentMapper;
import com.example.wisdom_book_management.service.AppointmentService;
import org.springframework.stereotype.Service;

@Service
public class AppointmentServiceImpl extends ServiceImpl<AppointmentMapper, Appointment> implements AppointmentService{

}
