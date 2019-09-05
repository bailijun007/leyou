package com.leyou.search.pojo.client;

import com.leyou.item.pojo.Category;
import com.leyou.item.pojo.api.CategoryApi;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("item-service")
public interface CategoryClient extends CategoryApi {


}
