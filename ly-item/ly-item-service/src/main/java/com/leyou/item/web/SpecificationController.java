package com.leyou.item.web;

import com.leyou.item.pojo.SpecGroup;
import com.leyou.item.pojo.SpecParam;
import com.leyou.item.service.SpecificationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
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
     * 根据组id查询参数
     * @param gid 组id
     * @return
     */
    @ApiOperation("根据组id查询参数")
    @GetMapping("groups")
    @ApiImplicitParam(name = "gid",value = "规格参数组id",paramType = "Long")
    public ResponseEntity<List<SpecParam>> queryGroupByGid(@RequestParam("gid")Long gid){
        return ResponseEntity.ok(specService.queryGroupByGid(gid));
    }
}
