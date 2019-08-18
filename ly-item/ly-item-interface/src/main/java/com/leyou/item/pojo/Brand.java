package com.leyou.item.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "tb_brand")
@ApiModel("品牌实体类")
public class Brand {

    @KeySql(useGeneratedKeys = true)
    @ApiModelProperty("品牌id")
    @Id
    private Long id;

    @ApiModelProperty("品牌名称")
    private String name;//品牌名称
    @ApiModelProperty("品牌图片")
    private String image;//品牌图片
    @ApiModelProperty("品牌的首字母")
    private Character letter;





}
