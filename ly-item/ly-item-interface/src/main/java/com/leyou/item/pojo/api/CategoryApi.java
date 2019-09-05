package com.leyou.item.pojo.api;

import com.leyou.item.pojo.Category;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface CategoryApi {

    @GetMapping("category/list/{ids}")
    List<Category> queryCategoryByIds(@PathVariable("ids") List<Long> ids);


}
