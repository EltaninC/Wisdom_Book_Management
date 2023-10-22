package com.example.wisdom_book_management;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.FastByIDMap;
import org.apache.mahout.cf.taste.impl.model.GenericDataModel;
import org.apache.mahout.cf.taste.impl.model.GenericPreference;
import org.apache.mahout.cf.taste.impl.model.GenericUserPreferenceArray;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.TanimotoCoefficientSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.model.PreferenceArray;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.apache.mahout.cf.taste.similarity.precompute.example.GroupLensDataModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SpringBootTest
class WisdomBookManagementApplicationTests {

    @Autowired
    MahoutMapper mahoutMapper;

    @Test
    void contextLoads() {
    }

    @Test
    void Mahout(){
        File file = new File("D:\\Project - Campus smart book management system\\test\\ratings.dat");
        // 实例化DataModel并将数据传入其内
        DataModel dataModel = null;
        try {
            //dataModel = new FileDataModel(file);
            dataModel = new GroupLensDataModel(file);
            System.out.println(dataModel);

        } catch (IOException e) {
            e.printStackTrace();
        }
        // 计算相似度
        ItemSimilarity itemSimilarity = null;
        try {
            itemSimilarity = new PearsonCorrelationSimilarity(dataModel);
            System.out.println(itemSimilarity);
        } catch (TasteException e) {
            e.printStackTrace();
        }
        // 构建推荐器，使用基于物品的协同过滤推荐
        GenericItemBasedRecommender recommender = new GenericItemBasedRecommender(dataModel, itemSimilarity);

        List<RecommendedItem> recommendedItemList = null;
        try {
            // 计算用户2当前浏览的商品2，推荐2个相似的商品
            recommendedItemList = recommender.recommendedBecause(2, 2, 2);
            System.out.println(recommendedItemList);
        } catch (TasteException e) {
            e.printStackTrace();
        }

        System.out.println("使用基于物品的协同过滤算法");
        System.out.println("根据用户2当前浏览的商品2，推荐2个相似的商品");
        for (RecommendedItem recommendedItem : recommendedItemList) {
            System.out.println(recommendedItem);
        }
        long start = System.currentTimeMillis();
        try {
            recommendedItemList = recommender.recommendedBecause(5, 1, 3);
        } catch (TasteException e) {
            e.printStackTrace();
        }

        System.out.println("使用基于物品的协同过滤算法");
        System.out.println("根据用户5当前浏览的商品1，推荐3个相似的商品");
        for (RecommendedItem recommendedItem : recommendedItemList) {
            System.out.println(recommendedItem);
        }
        System.out.println(System.currentTimeMillis() -start);

    }

    @Test
    public void MM() throws IOException, TasteException {
        //准备数据 这里是电影评分数据
        File file = new File("D:\\Project - Campus smart book management system\\test\\ratings.dat");
        //将数据加载到内存中，GroupLensDataModel是针对开放电影评论数据的
        DataModel dataModel = new GroupLensDataModel(file);
        //计算相似度，相似度算法有很多种，欧几里得、皮尔逊等等。
        UserSimilarity similarity = new PearsonCorrelationSimilarity(dataModel);
        //计算最近邻域，邻居有两种算法，基于固定数量的邻居和基于相似度的邻居，这里使用基于固定数量的邻居
        UserNeighborhood userNeighborhood = new NearestNUserNeighborhood(100, similarity, dataModel);
        //构建推荐器，协同过滤推荐有两种，分别是基于用户的和基于物品的，这里使用基于用户的协同过滤推荐
        Recommender recommender = new GenericUserBasedRecommender(dataModel, userNeighborhood, similarity);
        //给用户ID等于5的用户推荐10部电影
        List<RecommendedItem> recommendedItemList = recommender.recommend(5, 10);
        //打印推荐的结果
        System.out.println("使用基于用户的协同过滤算法");
        System.out.println("为用户5推荐10个商品");
        for (RecommendedItem recommendedItem : recommendedItemList) {
            System.out.println(recommendedItem);
        }
    }

    @Test
    public void recommend() throws TasteException {
        Integer userId = 2;
        List<com.example.wisdom_book_management.Mahout> userList = mahoutMapper.getData1();
        //创建数据模型
        DataModel dataModel = this.createDataModel(userList);
        //获取用户相似程度
        UserSimilarity similarity = new TanimotoCoefficientSimilarity(dataModel);
        //获取用户邻居
        UserNeighborhood userNeighborhood = new NearestNUserNeighborhood(10, similarity, dataModel);
        //构建推荐器
        Recommender recommender = new GenericUserBasedRecommender(dataModel, userNeighborhood, similarity);
        //推荐2个
        List<RecommendedItem> recommendedItems = recommender.recommend(userId, 2);
        List<Long> itemIds = recommendedItems.stream().map(RecommendedItem::getItemID).collect(Collectors.toList());
        System.out.println(recommendedItems);
    }
    private DataModel createDataModel(List<com.example.wisdom_book_management.Mahout> userArticleOperations) {
        FastByIDMap<PreferenceArray> fastByIdMap = new FastByIDMap<>();
        Map<Integer, List<com.example.wisdom_book_management.Mahout>> map = userArticleOperations.stream().collect(Collectors.groupingBy(com.example.wisdom_book_management.Mahout::getUser_id));
        Collection<List<com.example.wisdom_book_management.Mahout>> list = map.values();
        for(List<com.example.wisdom_book_management.Mahout> userPreferences : list){
            GenericPreference[] array = new GenericPreference[userPreferences.size()];
            for(int i = 0; i < userPreferences.size(); i++){
                com.example.wisdom_book_management.Mahout userPreference = userPreferences.get(i);
                GenericPreference item = new GenericPreference(userPreference.getUser_id(), userPreference.getBook_id(), userPreference.getValue());
                array[i] = item;
            }
            fastByIdMap.put(array[0].getUserID(), new GenericUserPreferenceArray(Arrays.asList(array)));
        }
        return new GenericDataModel(fastByIdMap);
    }

    @Test
    public void getRecommendedItem() throws IOException, TasteException {
        //准备数据 这里是书籍借阅数据
        List<Mahout> userBooks =  mahoutMapper.getData1();
        //将数据加载到内存中，GroupLensDataModel是针对开放电影评论数据的
        DataModel dataModel = this.createDataModel(userBooks);
        //计算相似度，相似度算法有很多种，欧几里得、皮尔逊等等。
        UserSimilarity similarity = new TanimotoCoefficientSimilarity(dataModel);
        //计算最近邻域，邻居有两种算法，基于固定数量的邻居和基于相似度的邻居，这里使用基于固定数量的邻居
        UserNeighborhood userNeighborhood = new NearestNUserNeighborhood(100, similarity, dataModel);
        //构建推荐器，协同过滤推荐有两种，分别是基于用户的和基于物品的，这里使用基于用户的协同过滤推荐
        Recommender recommender = new GenericUserBasedRecommender(dataModel, userNeighborhood, similarity);
        //给用户ID等于5的用户推荐10部电影
        List<RecommendedItem> recommendedItemList = recommender.recommend(3, 10);
        //打印推荐的结果
        System.out.println("使用基于用户的协同过滤算法");
        System.out.println("为用户"+1+"推荐10个商品");
        for (RecommendedItem recommendedItem : recommendedItemList) {
            System.out.println(recommendedItem);
        }
    }

}
