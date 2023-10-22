package com.example.wisdom_book_management.controller;

import com.example.wisdom_book_management.domain.Book;
import com.example.wisdom_book_management.domain.Statistics;
import com.example.wisdom_book_management.service.StatisticsService;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/statistics")
public class StatisticsController {
    @Autowired
    StatisticsService statisticsService;

    //获取各类别的借阅数量
    @RequestMapping("/proportion")
    public List<Statistics> getTypeProportion(){
        return statisticsService.getTypeProportion();
    }

    //获取各书本借阅数量
    @RequestMapping("/ranking")
    public List<Statistics> getBookRanking(){
        return statisticsService.getBookRanking();
    }

    //获取用户推荐
    @RequestMapping("/recommendedItem")
    public List<Book> getRecommendedItem(int user_id) throws IOException, TasteException {
        return statisticsService.getRecommendedItem(user_id);
    }
}
