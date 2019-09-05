package com.leyou.search.pojo.client;

import com.leyou.item.pojo.api.GoodsApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("item-service")
public interface GoodsClient extends GoodsApi {

}
