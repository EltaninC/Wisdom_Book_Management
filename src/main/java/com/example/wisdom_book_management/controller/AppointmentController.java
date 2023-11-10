package com.example.wisdom_book_management.controller;

import com.example.wisdom_book_management.component.Result;
import com.example.wisdom_book_management.domain.po.Appointment;
import com.example.wisdom_book_management.service.AppointmentService;
import com.example.wisdom_book_management.utils.ResultUtils;
import com.example.wisdom_book_management.utils.TokenUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

import static com.example.wisdom_book_management.component.ResultEnum.DATA_IS_NULL;

@RestController
public class AppointmentController {

    @Autowired
    AppointmentService appointmentService;
    //图书预约
    //TODO
//    @RequestMapping("/make_appointment")
//    public String InsertAppointment(HttpServletRequest request){
//        Appointment appointment = new Appointment();
//        appointment.setBook_id(Integer.parseInt(request.getParameter("book_id")));
//        appointment.setUser_id((Integer) request.getSession().getAttribute("Uid"));
//        LocalDate date = LocalDate.now();
//        appointment.setAppointment_date(date.plusDays(1));
//        appointmentService.InsertAppointment(appointment);
//        return "book";
//    }
//
    //通过用户id查询预约记录
    @GetMapping ("/appointment")
    public Result SelectAppointmentByUserId(HttpServletRequest request){
        //1.获取用户id
        String token = request.getHeader("token");
        int uid = Integer.parseInt(TokenUtil.getUserId(token));
        //2.查询预约记录
        List<Appointment> appointment = appointmentService.listByIds(Collections.singleton(uid));
        //3.返回结果
        if(appointment.isEmpty()){
            return ResultUtils.Err(DATA_IS_NULL.getCode(), DATA_IS_NULL.getMsg());
        }
        return ResultUtils.success(appointment);
    }

}
