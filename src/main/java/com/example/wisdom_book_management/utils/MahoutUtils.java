package com.example.wisdom_book_management.utils;

import com.example.wisdom_book_management.domain.dto.Mahout;
import com.example.wisdom_book_management.mapper.StatisticsMapper;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.FastByIDMap;
import org.apache.mahout.cf.taste.impl.model.GenericDataModel;
import org.apache.mahout.cf.taste.impl.model.GenericPreference;
import org.apache.mahout.cf.taste.impl.model.GenericUserPreferenceArray;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.TanimotoCoefficientSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.model.PreferenceArray;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MahoutUtils {

    private StatisticsMapper statisticsMapper;

    public MahoutUtils(StatisticsMapper statisticsMapper){
        this.statisticsMapper=statisticsMapper;
    }

    public List<RecommendedItem> getRecommendedItem(int user_id) throws IOException, TasteException {
        //准备数据 这里是书籍借阅数据
        List<Mahout> userBooks = statisticsMapper.getUserBook();
        //将数据加载到内存中，GroupLensDataModel是针对开放电影评论数据的
        DataModel dataModel = this.createDataModel(userBooks);
        //计算相似度，相似度算法有很多种，欧几里得、皮尔逊等等。
        UserSimilarity similarity = new TanimotoCoefficientSimilarity(dataModel);
        //计算最近邻域，邻居有两种算法，基于固定数量的邻居和基于相似度的邻居，这里使用基于固定数量的邻居
        UserNeighborhood userNeighborhood = new NearestNUserNeighborhood(100, similarity, dataModel);
        //构建推荐器，协同过滤推荐有两种，分别是基于用户的和基于物品的，这里使用基于用户的协同过滤推荐
        Recommender recommender = new GenericUserBasedRecommender(dataModel, userNeighborhood, similarity);
        //给用户ID等于5的用户推荐10部电影
        //打印推荐的结果
//        System.out.println("使用基于用户的协同过滤算法");
//        System.out.println("为用户"+user_id+"推荐10个商品");
//        for (RecommendedItem recommendedItem : recommendedItemList) {
//            System.out.println(recommendedItem);
//        }
        return recommender.recommend(user_id, 10);
    }

    //自定义DataModel创建
    private DataModel createDataModel(List<Mahout> userBooks) {
        FastByIDMap<PreferenceArray> fastByIdMap = new FastByIDMap<>();
        Map<Integer, List<Mahout>> map = userBooks.stream().collect(Collectors.groupingBy(Mahout::getUser_id));
        Collection<List<Mahout>> list = map.values();
        for(List<Mahout> userPreferences : list){
            GenericPreference[] array = new GenericPreference[userPreferences.size()];
            for(int i = 0; i < userPreferences.size(); i++){
                Mahout userPreference = userPreferences.get(i);
                GenericPreference item = new GenericPreference(userPreference.getUser_id(), userPreference.getBook_id(), userPreference.getValue());
                array[i] = item;
            }
            fastByIdMap.put(array[0].getUserID(), new GenericUserPreferenceArray(Arrays.asList(array)));
        }
        return new GenericDataModel(fastByIdMap);
    }
}
