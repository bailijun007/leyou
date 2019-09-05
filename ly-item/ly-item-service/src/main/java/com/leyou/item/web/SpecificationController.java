package com.leyou.item.web;

import com.leyou.item.pojo.SpecGroup;
import com.leyou.item.pojo.SpecParam;
import com.leyou.item.service.SpecificationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("spec")
@Api(tags = "规格参数相关请求")
public class SpecificationController {
    @Autowired
    private SpecificationService  specService;


    /**
     * 根据分类id查询规格组
     * @param cid 商品分类id
     * @return
     */
    @ApiOperation("根据分类id查询规格组")
    @GetMapping("groups/{cid}")
    @ApiImplicitParam(name = "cid",value = "商品分类id")
    public ResponseEntity<List<SpecGroup>> queryGroupByCid(@PathVariable("cid") Long cid){
        return ResponseEntity.ok(specService.queryGroupByCid(cid));
    }

    /**
     * 查询参数集合
     * @param gid 组id
    * @param cid 分类id
     * @param searching 是否搜索
     * @return
     */
    @ApiOperation("查询参数集合")
    @GetMapping("params")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "gid",value = "组id",paramType = "Long"),
            @ApiImplicitParam(name = "cid",value = "分类id",paramType = "Long"),
            @ApiImplicitParam(name = "searching",value = "是否搜索",paramType = "Boolean")
    })
    public ResponseEntity<List<SpecParam>> queryParamList(
            @RequestParam(value = "gid",required = false) Long gid,
            @RequestParam(value = "cid",required = false) Long cid,
            @RequestParam(value = "searching",required = false) Boolean searching){
        return ResponseEntity.ok(specService.queryParamList(gid,cid,searching));
    }
}
