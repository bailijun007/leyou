package com.leyou.search.repository;

import com.leyou.search.pojo.Goods;
import com.leyou.search.pojo.repository.GoodsRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GoodsRepositoryTest {
    @Autowired
    private ElasticsearchTemplate template;

    @Autowired
    private GoodsRepository goodsRepository;


    @Test
    public void testCreateIndex(){
        //创建索引库
        template.createIndex(Goods.class);
        //映射关系
        template.putMapping(Goods.class);
    }



}
