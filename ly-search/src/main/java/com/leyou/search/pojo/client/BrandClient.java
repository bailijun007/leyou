package com.leyou.search.pojo.client;

import com.leyou.item.pojo.api.BrandApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("item-service")
public interface BrandClient extends BrandApi {
}
