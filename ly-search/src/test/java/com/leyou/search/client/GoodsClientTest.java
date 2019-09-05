package com.leyou.search.client;

import com.leyou.item.pojo.SpuDetail;
import com.leyou.search.pojo.client.GoodsClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GoodsClientTest {
    @Autowired
    private GoodsClient goodsClient;


    @Test
    public void queryDetailById(){
        SpuDetail spuDetail = goodsClient.queryDetailById(2L);
        System.out.println("spuDetail = " + spuDetail);
    }

}
