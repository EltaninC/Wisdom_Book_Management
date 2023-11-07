package com.example.wisdom_book_management.service;

import com.example.wisdom_book_management.domain.po.Book;
import com.example.wisdom_book_management.domain.dto.Statistics;
import org.apache.mahout.cf.taste.common.TasteException;

import java.io.IOException;
import java.util.List;

public interface StatisticsService {
    List<Statistics> getTypeProportion();

    List<Statistics> getBookRanking();

    List<Book> getRecommendedItem(int user_id) throws IOException, TasteException;
}
