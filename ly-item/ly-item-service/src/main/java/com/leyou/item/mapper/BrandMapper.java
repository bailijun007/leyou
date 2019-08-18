package com.leyou.item.mapper;

import com.leyou.item.pojo.Brand;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface BrandMapper extends Mapper<Brand> {
    @Insert("insert into tb_category_brand VALUES (#{cid},#{bid})")
    int insertCategoryBrand(@Param("cid")long cid,@Param("bid")long bid);

    @Select("select b.id,b.`name` FROM tb_brand b INNER JOIN tb_category_brand cb ON b.id=cb.brand_id where cb.brand_id=#{cid}")
    List<Brand> queryBrandByCid(@Param("cid") Long cid);
}
