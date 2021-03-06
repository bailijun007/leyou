package com.leyou.item.web;

import com.leyou.common.vo.PageResult;
import com.leyou.item.pojo.Sku;
import com.leyou.item.pojo.Spu;
import com.leyou.item.pojo.SpuDetail;
import com.leyou.item.service.GoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "商品相关请求")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    /**
     * 分页查询spu
     * @param page
     * @param rows
     * @param key
     * @param saleable
     * @return
     */
    @ApiOperation("分页查询spu")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value = "当前页",paramType = "Integer"),
            @ApiImplicitParam(name = "rows",value = "每页显示条数",paramType = "Integer"),
            @ApiImplicitParam(name = "saleable",value = "是否上架",paramType = "Boolean"),
            @ApiImplicitParam(name = "key",value = "搜索关键字")
    })
    @GetMapping("/spu/page")
    public ResponseEntity<PageResult<Spu>> querySpuByPage(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows,
            @RequestParam(value = "saleable",defaultValue = "true") Boolean saleable,
            @RequestParam(value = "key", required = false) String key
           ){

             return ResponseEntity.ok(goodsService.querySpuByPage(page,rows,saleable,key));
    }

    /**
     * 商品新增
     * @param spu
     * @return
     */
    @ApiOperation("商品新增")
    @PostMapping("goods")
    public ResponseEntity<Void> saveGoods(@RequestBody Spu spu){
        goodsService.saveGoods(spu);
        return ResponseEntity.status(HttpStatus.CREATED).build();//无返回值用ResponseEntity.status(HttpStatus.CREATED).build()
    }

    /**
     * 根据spu的id查询详情detail
     * @param id
     * @return
     */
    @ApiOperation("根据spuid查询详情")
    @ApiImplicitParam(name = "id",value = "spu的id",paramType = "Long")
    @GetMapping("/spu/detail/{id}")
    public ResponseEntity<SpuDetail> queryDetailById(@PathVariable("id") Long id){
        return ResponseEntity.ok(goodsService.queryDetailById(id));
    }

    /**
     * 根据spuid查询下面所有的sku
     * @param id
     * @return
     */
    @ApiOperation("根据spuid查询下面所有的sku")
    @ApiImplicitParam(name = "id",value = "spu的id",paramType = "Long")
    @GetMapping("/sku/list")
    public ResponseEntity<List<Sku>> querySkuBySpuid(@RequestParam("id") Long id){
        return ResponseEntity.ok(goodsService.querySkuBySpuid(id));
    }

    /**
     * 商品修改
     * @param spu
     * @return
     */
    @ApiOperation("商品修改")
    @PutMapping("goods")
    public ResponseEntity<Void> updateGoods(@RequestBody Spu spu){
        goodsService.updateGoods(spu);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }



}
