package com.example.wisdom_book_management.service;

import com.example.wisdom_book_management.domain.Appointment;
import com.example.wisdom_book_management.mapper.AppointmentMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {
    @Autowired
    AppointmentMapper appointmentMapper;

    public void InsertAppointment(Appointment appointment){
        appointmentMapper.InsertAppointment(appointment);
    }

    public List<Appointment> SelectAppointmentByUserId(int user_id){
        return appointmentMapper.SelectAppointmentByUserId(user_id);
    }

}
