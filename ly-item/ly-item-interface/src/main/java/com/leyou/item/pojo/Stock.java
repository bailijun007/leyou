package com.leyou.item.pojo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@ApiModel("库存实体类")
@Table(name = "tb_stock")
public class Stock {

    @Id
    @ApiModelProperty("库存对应的商品sku id")
    private Long skuId;
    /**
     * 秒杀可用库存
     */
    @ApiModelProperty("秒杀可用库存")
    private Integer seckillStock;
    /**
     * 已秒杀数量
     */
    @ApiModelProperty("秒杀总数量")
    private Integer seckillTotal;
    /**
     * 正常库存
     */
    @ApiModelProperty("正常库存数量")
    private Long stock;
}

