package com.leyou.item.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

@ApiModel("商品类目实体类")
@Data
@Table(name = "tb_category")
public class Category {
    @Id
    @KeySql(useGeneratedKeys = true)
    @ApiModelProperty("类目id")
    private Long id;
    @ApiModelProperty("类目名称")
    private  String name;
    @ApiModelProperty("父类目id,顶级类目填0")
    private Long parentId;
    @ApiModelProperty("是否为父节点，0为否，1为是")
    private Boolean isParent;
    @ApiModelProperty("排序指数，越小越靠前")
    private Integer sort;
}
