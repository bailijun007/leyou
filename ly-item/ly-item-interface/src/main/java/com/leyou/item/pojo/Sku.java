package com.leyou.item.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.*;
import java.util.Date;

@ApiModel("sku实体类")
@Data
@Table(name = "tb_sku")
public class Sku {
    @Id
    @KeySql(useGeneratedKeys = true)
    @ApiModelProperty("sku id,主键")
    private Long id;
    @ApiModelProperty("spu id")
    private Long spuId;
    @ApiModelProperty("商品标题")
    private String title;
    @ApiModelProperty("商品的图片，多个图片以‘,’分割")
    private String images;
    @ApiModelProperty("销售价格，单位为分")
    private Long price;
    /**
     * 商品特殊规格的键值对
     */
    @ApiModelProperty("sku的特有规格参数键值对")
    private String ownSpec;
    /**
     * 商品特殊规格的下标
     */
    @ApiModelProperty("特有规格属性在spu属性模板中的对应下标组合")
    private String indexes;
    /**
     * 是否有效，逻辑删除用
     */
    @ApiModelProperty("是否有效")
    private Boolean enable;
    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private Date createTime;
    /**
     * 最后修改时间
     */
    @ApiModelProperty("最后修改时间")
    private Date lastUpdateTime;

    @ApiModelProperty("库存")
    @Transient
    /**
     * @Transient 表示该属性并非一个到数据库表的字段的映射,ORM框架将忽略该属性.
     */
    private Long stock;//库存
}

