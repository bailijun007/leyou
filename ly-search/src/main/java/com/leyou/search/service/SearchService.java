package com.leyou.search.service;

import com.leyou.common.enums.ExceptionEnums;
import com.leyou.common.exception.LyException;
import com.leyou.common.utils.JsonUtils;
import com.leyou.item.pojo.*;
import com.leyou.search.pojo.Goods;
import com.leyou.search.pojo.client.BrandClient;
import com.leyou.search.pojo.client.CategoryClient;
import com.leyou.search.pojo.client.GoodsClient;
import com.leyou.search.pojo.client.SpecificationClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SearchService {
@Autowired
private CategoryClient categoryClient;

@Autowired
private BrandClient brandClient;

@Autowired
private GoodsClient goodsClient;

@Autowired
private SpecificationClient specificationClient;

    public Goods buildGoods(Spu spu){
       //查询分类
        List<Category> categories = categoryClient.queryCategoryByIds
                (Arrays.asList(spu.getCid1(), spu.getCid2(), spu.getCid3()));
        if (CollectionUtils.isEmpty(categories)) {
            throw new LyException(ExceptionEnums.CATEGORY_NOT_BE_FOND);
        }
        List<String> names = categories.stream().map(Category::getName).collect(Collectors.toList());
        //搜索品牌
        Brand brand = brandClient.queryBrandById(spu.getBrandId());
        if (brand == null) {
            throw new LyException(ExceptionEnums.BEAND_CANNOT_BE_FOND);
        }

        //搜索字段
        String all=spu.getTitle()+StringUtils.join(names,"")+brand.getName();

        //查询sku
        List<Sku>  skuList= goodsClient.querySkuBySpuid(spu.getId());
        if(CollectionUtils.isEmpty(skuList)){
            throw new LyException(ExceptionEnums.GOODS_SKU_NOT_FOND);
        }
       //价格集合
        Set<Long> prices=new HashSet<>();
        //对sku进行处理
        List<Map<String,Object>> skus=new ArrayList<>();
        for (Sku sku : skuList) {
            Map<String,Object> map=new HashMap<>();
            map.put("id",sku.getId());
            map.put("title",sku.getTitle());
            map.put("price",sku.getPrice());
            map.put("image",StringUtils.substringBefore(sku.getImages(),","));
            skus.add(map);
            //对价格进行处理
            prices.add(sku.getPrice());
        }

        //查询规格参数
        List<SpecParam> params = specificationClient.queryParamList(null, spu.getCid3(), true);
        if(CollectionUtils.isEmpty(params)){
            throw new LyException(ExceptionEnums.SPEC_PARAM_NOT_FOND);
        }
        //查询商品详情
        SpuDetail spuDetail = goodsClient.queryDetailById(spu.getId());
        //TODO 没写完
        //获取通用规格参数

        //获取特有规格参数

        //规格参数(KEY:存储的在SpecParam中，值:存储在商品详情里)
        Map<String,Object> map=new HashMap<>();

        Goods goods = new Goods();
        goods.setBrandId(spu.getBrandId());
        goods.setCid1(spu.getCid1());
        goods.setCid2(spu.getCid2());
        goods.setCid3(spu.getCid3());
        goods.setCreateTime(spu.getCreateTime());
        goods.setAll(all);//搜索字段，包含标题，分类，品牌，规格等
        goods.setPrice(prices);//所有sku的价格集合
        goods.setSkus(JsonUtils.serialize(skus));//所有sku集合的json格式
        goods.setSpecs(null);//所有可搜索的规格参数
        goods.setSubTitle(spu.getSubTitle());

        return goods;
    }
}
