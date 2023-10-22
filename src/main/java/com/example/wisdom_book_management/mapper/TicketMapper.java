package com.example.wisdom_book_management.mapper;

import com.example.wisdom_book_management.domain.Ticket;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TicketMapper {
    @Insert("Insert Into ticket(user_id,book_code,ticket_type,ticket_description,) " +
            "Values(#{ticket.user_id},#{ticket.book_code},#{ticket.ticket_type},#{ticket.ticket_description}," +
            "#{ticket.ticket_money}")
    void InsertTicket(@Param("ticket")Ticket ticket);

    @Select("Select * from ticket")
    List<Ticket> SelectTicket();
}
