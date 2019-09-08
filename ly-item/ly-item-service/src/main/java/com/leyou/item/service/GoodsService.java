package com.leyou.item.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leyou.common.enums.ExceptionEnums;
import com.leyou.common.exception.LyException;
import com.leyou.common.vo.PageResult;
import com.leyou.item.mapper.SkuMapper;
import com.leyou.item.mapper.SpuDetailMapper;
import com.leyou.item.mapper.SpuMapper;
import com.leyou.item.mapper.StockMapper;
import com.leyou.item.pojo.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class GoodsService {
    @Autowired
    private SpuMapper spuMapper;
    @Autowired
    private SpuDetailMapper spuDetailMapper;
    @Autowired
    private SkuMapper skuMapper;
    @Autowired
    private  CategoryService categoryService;
    @Autowired
    private StockMapper stockMapper;
    @Autowired
    private BrandService brandService;

    public PageResult<Spu> querySpuByPage(Integer page, Integer rows, Boolean saleable, String key) {
        //分页
        PageHelper.startPage(page,rows);
        //过滤
        Example example = new Example(Spu.class);
        Example.Criteria criteria = example.createCriteria();
        //搜索字段过滤（这里搜索关键词主要指标题）
        if(StringUtils.isNotBlank(key)){//搜索字段不为空时
            criteria.andLike("title","%"+key+"%");
        }
        //上下架过滤
        if(null!=saleable){//上下架不为空时
            criteria.andEqualTo("saleable",saleable);
        }
        //默认排序
        example.setOrderByClause("last_update_time desc");

        //查询
        List<Spu> spus = spuMapper.selectByExample(example);
        if(CollectionUtils.isEmpty(spus)){
            throw  new LyException(ExceptionEnums.GOODS_NOT_FOND);
        }

        //解析分类和品牌名称
        loadCategoryAndBrandName(spus);

        //解析分页结果
        PageInfo<Spu> pageInfo = new PageInfo<>(spus);
        return new PageResult<>(pageInfo.getTotal(),spus);
    }

    private void loadCategoryAndBrandName(List<Spu> spus) {
        for (Spu spu : spus) {
            //处理品牌分类名称
            List<String> names = categoryService.queryById(Arrays.asList(spu.getCid1(), spu.getCid2(), spu.getCid3()))
                    .stream().map(Category::getName).collect(Collectors.toList());
            spu.setCname(StringUtils.join(names,"/"));//StringUtils.join()可以把一个集合，以/分割成字符串
        //处理品牌名称
            spu.setBname(brandService.queryById(spu.getBrandId()).getName());
        }
    }

    @Transactional
    public void saveGoods(Spu spu) {
        //新增spu
        spu.setId(null);
        spu.setCreateTime(new Date());
        spu.setLastUpdateTime(spu.getCreateTime());
        spu.setSaleable(true);//是否上架
        spu.setValid(false);//是否有效
        int count = spuMapper.insert(spu);
        if (count!=1){
            throw new LyException(ExceptionEnums.INSERT_GOODS_EORRR);
        }
        //新增SpuDetail
        SpuDetail detail = spu.getSpuDetail();
        detail.setSpuId(spu.getId());
        spuDetailMapper.insert(detail);
        //新增Sku
        List<Sku> skus = spu.getSkus();
        List<Stock> stockList=new ArrayList<>();
        for (Sku sku : skus) {
            sku.setCreateTime(new Date());
            sku.setLastUpdateTime(sku.getCreateTime());
            sku.setSpuId(spu.getId());
             count = skuMapper.insert(sku);
            if (count!=1){
                throw new LyException(ExceptionEnums.INSERT_GOODS_EORRR);
            }

            Stock stock = new Stock();
            stock.setSkuId(sku.getId());
            stock.setStock(sku.getStock());
           // 方法一 批量新增库存
            /*count = stockMapper.insert(stock);
            if (count!=1){
                throw new LyException(ExceptionEnums.INSERT_GOODS_EORRR);
            }*/


            // 方法2 批量新增库存
            stockList.add(stock);
        }
        // 方法2 批量新增库存
        count= stockMapper.insertList(stockList);
        if(count!=stockList.size()){
            throw new LyException(ExceptionEnums.INSERT_GOODS_EORRR);
        }
    }

    public SpuDetail queryDetailById(Long id) {
        SpuDetail detail = spuDetailMapper.selectByPrimaryKey(id);
        if(null==detail){
            throw new LyException(ExceptionEnums.GOODS_DETAIL_NOT_FOND);
        }
        return detail;
    }

    public List<Sku> querySkuBySpuid(Long spuId) {
      //查询sku
        Sku sku = new Sku();
        sku.setSpuId(spuId);
        List<Sku> skuList = skuMapper.select(sku);
        if(CollectionUtils.isEmpty(skuList)){
            throw new LyException(ExceptionEnums.GOODS_SKU_NOT_FOND);
        }
        //查询库存(方法一)
//        for (Sku s : skuList) {
//            Stock stock = stockMapper.selectByPrimaryKey(s.getSpuId());
//            if(null==stock){
//                throw new LyException(ExceptionEnums.GOODS_STOCK_NOT_FOND);
//            }
//            s.setStock(stock.getStock());
//        }

        //查询库存(方法2)
        List<Long> ids = skuList.stream().map(Sku::getId).collect(Collectors.toList());
        List<Stock> stockList = stockMapper.selectByIdList(ids);
        if(CollectionUtils.isEmpty(stockList)){
            throw new LyException(ExceptionEnums.GOODS_STOCK_NOT_FOND);
        }
        //把stock变成一个map，其中key是skuid，值是库存量
        Map<Long, Long> stockMap = stockList.stream().collect(Collectors.toMap(Stock::getSkuId, Stock::getStock));
        skuList.forEach(s->s.setStock(stockMap.get(s.getId())));
        return skuList;
    }

    public void updateGoods(Spu spu) {
        if (spu.getId()==null){
            throw new LyException(ExceptionEnums.GOODS_ID_NOT_BE_FON);
        }
        Sku sku = new Sku();
        sku.setId(spu.getId());
        //查询sku
        List<Sku> skuList = skuMapper.select(sku);
        if (!CollectionUtils.isEmpty(skuList)) {
            //删除sku
            skuMapper.delete(sku);
            //删除stock
            List<Long> ids = skuList.stream().map(Sku::getId).collect(Collectors.toList());
             stockMapper.deleteByIdList(ids);
        }
        spu.setLastUpdateTime(new Date());
        spu.setValid(null);
        spu.setCreateTime(null);
        spu.setSaleable(null);
        //修改spu
        int i = spuMapper.updateByPrimaryKeySelective(spu);
        if (i != 1) {
            throw new LyException(ExceptionEnums.UPDATE_GOODS_EORRR);
        }
        //修改detail
         i = spuDetailMapper.updateByPrimaryKeySelective(spu.getSpuDetail());
        if (i != 1) {
            throw new LyException(ExceptionEnums.UPDATE_GOODS_EORRR);
        }
        //新增sku和stock
        saveGoods(spu);

    }
}
