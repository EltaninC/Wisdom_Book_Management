package com.example.wisdom_book_management.controller;

import com.example.wisdom_book_management.domain.po.Appointment;
import com.example.wisdom_book_management.service.impl.AppointmentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;

@Controller
public class AppointmentController {

    @Autowired
    AppointmentService appointmentService;

    //图书预约
    @RequestMapping("/make_appointment")
    public String InsertAppointment(HttpServletRequest request){
        Appointment appointment = new Appointment();
        appointment.setBook_id(Integer.parseInt(request.getParameter("book_id")));
        appointment.setUser_id((Integer) request.getSession().getAttribute("Uid"));
        LocalDate date = LocalDate.now();
        appointment.setAppointment_date(date.plusDays(1));
        appointmentService.InsertAppointment(appointment);
        return "book";
    }

    //通过用户id查询预约记录
    @RequestMapping("/appointment")
    public String SelectAppointmentByUserId(HttpSession session){
        session.setAttribute("AppointmentList",
                appointmentService.SelectAppointmentByUserId((Integer) session.getAttribute("Uid")));
        return "appointment";
    }

}
