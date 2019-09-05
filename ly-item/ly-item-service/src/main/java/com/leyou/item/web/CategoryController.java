package com.leyou.item.web;

import com.leyou.item.pojo.Category;
import com.leyou.item.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("category")
@Api(tags = "商品类目相关请求")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     *根据父类目id查询商品分类
     * @param pid 父节点id
     * @return
     */
    @ApiOperation("根据父类目id查询商品分类")
    @ApiImplicitParam(name = "pid",value = "父节点id",paramType = "Long")
    @GetMapping("list")
    public ResponseEntity<List<Category>> queryCategoryByPid(@RequestParam("pid") Long pid){
      //  ResponseEntity.status(HttpStatus.OK).body(null); 可以简写为以下写法
      return   ResponseEntity.ok(categoryService.queryCategoryByPid(pid));

    }
    @ApiOperation("根据id查询商品分类")
    @ApiImplicitParam(name = "ids",value = "分类id")
    @GetMapping("list/{ids}")
    public ResponseEntity<List<Category>> queryCategoryByIds(@PathVariable("ids") List<Long> ids){
    return ResponseEntity.ok(categoryService.queryById(ids));
    }

}
