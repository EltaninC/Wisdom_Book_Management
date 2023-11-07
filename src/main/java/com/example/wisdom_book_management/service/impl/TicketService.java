package com.example.wisdom_book_management.service.impl;

import com.example.wisdom_book_management.domain.Ticket;
import com.example.wisdom_book_management.mapper.TicketMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {

    @Autowired
    TicketMapper ticketMapper;

    public List<Ticket> SelectTicket(){
        return ticketMapper.SelectTicket();
    }
}
