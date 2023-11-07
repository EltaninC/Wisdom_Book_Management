package com.example.wisdom_book_management.mapper;

import com.example.wisdom_book_management.domain.po.Borrow;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface BorrowMapper {
    @Select("select * from borrow where user_id = #{uid}")
    List<Borrow> GetBorrowByUid(Object uid);

    @Select("select * from borrow where borrow_id = #{id}")
    Borrow GetBorrowById(Object id);

    @Select("select * from borrow")
    List<Borrow> GetBorrow();

    @Select("select Count(*) from borrow where user_id = #{uid}")
    int GetBorrowCountById(int user_id);

    @Select("select Count(*) from borrow")
    int GetBorrowCount();

    @Select("select Count(*) from borrow where borrow_date like '${day}%'")
    int GetBorrowCountByDay(@Param("day") LocalDate day);

    @Select("select * from borrow where book_code = #{book_code}")
    Borrow GetBorrowByBookCode(String book_code);

    @Insert("insert into borrow (user_id,book_code,borrow_date,expect_return_date,renew)" +
            "VALUES(#{borrow.user_id},#{borrow.book_code},#{borrow.borrow_date}" +
            ",#{borrow.expect_return_date},#{borrow.renew})")
    void InsertBorrow(@Param("borrow") Borrow borrow);

    @Delete("delete from borrow where borrow_id = #{id}")
    void DeleteBorrow(int id);

    @Select("select renew from borrow where borrow_id = #{id}")
    int isRenew(int id);

    @Update("update borrow SET renew = 1, expect_return_date = expect_return_date * 2 - borrow_date " +
            "WHERE borrow_id = #{id}")
    void Renew(int id);
}
