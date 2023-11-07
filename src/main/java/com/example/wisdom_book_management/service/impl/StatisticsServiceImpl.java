package com.example.wisdom_book_management.service.impl;

import com.example.wisdom_book_management.domain.Back;
import com.example.wisdom_book_management.domain.Book;
import com.example.wisdom_book_management.domain.Statistics;
import com.example.wisdom_book_management.mapper.BookMapper;
import com.example.wisdom_book_management.mapper.StatisticsMapper;
import com.example.wisdom_book_management.service.StatisticsService;
import com.example.wisdom_book_management.utils.MahoutUtils;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@Service
public class StatisticsServiceImpl implements StatisticsService {
    @Autowired
    StatisticsMapper statisticsMapper;

    @Autowired
    BookMapper bookMapper;

    @Override
    public List<Statistics> getTypeProportion() {
        return statisticsMapper.getTypeProportion();
    }

    @Override
    public List<Statistics> getBookRanking() {
        return statisticsMapper.getBookRanking();
    }

    @Override
    public List<Book> getRecommendedItem(int user_id) throws IOException, TasteException {
        MahoutUtils mahoutUtils = new MahoutUtils(statisticsMapper);
        List<Book> books = new LinkedList<>();
        //获取推荐书本id
        List<RecommendedItem>  recommendedItemList = mahoutUtils.getRecommendedItem(user_id);
        for(RecommendedItem recommendedItem : recommendedItemList){
            int i = 0;
            Book book = bookMapper.GetBookById((int) recommendedItem.getItemID());
            books.add(i++,book);
        }
        return books;
    }

}
