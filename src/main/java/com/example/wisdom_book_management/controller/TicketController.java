package com.example.wisdom_book_management.controller;

import com.example.wisdom_book_management.domain.Ticket;
import com.example.wisdom_book_management.service.impl.TicketService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class TicketController {

    @Autowired
    TicketService ticketService;

    @RequestMapping("/select_ticket")
    public String SelectTicket(HttpSession session){
        List<Ticket> tickets = ticketService.SelectTicket();
        session.setAttribute("TicketList",tickets);
        return "ticket";
    }
}
