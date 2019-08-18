package com.leyou.item.service;

import com.leyou.common.enums.ExceptionEnums;
import com.leyou.common.exception.LyException;
import com.leyou.item.pojo.Category;
import com.leyou.item.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;


    public List<Category> queryCategoryByPid(Long pid) {
        Category category=new Category();
        category.setParentId(pid);
    //categoryMapper.select(category); 需要我们new一个对象，然后会根据这个对象的非空字段作为查询的条件
        List<Category> list = categoryMapper.select(category);
      //判断查询结果
        if (CollectionUtils.isEmpty(list)){
        throw new LyException(ExceptionEnums.CATEGORY_NOT_BE_FOND);
        }
        return list;
    }

    public List<Category> queryById(List<Long> ids){
        List<Category> list = categoryMapper.selectByIdList(ids);
        if (CollectionUtils.isEmpty(list)){
            throw new LyException(ExceptionEnums.CATEGORY_NOT_BE_FOND);
        }
        return list;
    }
}
