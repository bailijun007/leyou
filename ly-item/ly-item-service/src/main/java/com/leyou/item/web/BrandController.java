package com.leyou.item.web;

import com.leyou.common.vo.PageResult;
import com.leyou.item.pojo.Brand;
import com.leyou.item.service.BrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "商品品牌相关请求")
@RequestMapping("/brand")
public class BrandController {
    @Autowired
    private BrandService brandService;

    /**
     * 分页查询品牌
     * @param page
     * @param rows
     * @param sortBy
     * @param desc
     * @param key
     * @return
     */
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value = "当前页数",paramType = "Integer"),
            @ApiImplicitParam(name = "rows",value = "每页显示条数",paramType = "Integer"),
            @ApiImplicitParam(name = "sortBy",value = "按什么排序"),
            @ApiImplicitParam(name = "desc",value = "是否降序显示",paramType = "Boolean"),
            @ApiImplicitParam(name = "key",value = "搜索关键字")
    })
    @GetMapping("page")
    public ResponseEntity<PageResult<Brand>> queryBrandByPage(
            @RequestParam(value = "page",defaultValue = "1")Integer page,
            @RequestParam(value = "rows",defaultValue = "5")Integer rows,
            @RequestParam(value = "sortBy",required = false)String sortBy,
            @RequestParam(value = "desc",defaultValue ="false")Boolean desc,
            @RequestParam(value = "key",required = false)String key
    ){
            return ResponseEntity.ok(brandService.queryBrandByPage(page,rows,sortBy,desc,key));
    }

    @PostMapping
    public ResponseEntity<Void> savaBrand(Brand brand,@RequestParam("cids") List<Long> cids){
        brandService.savaBrand(brand,cids);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


}
