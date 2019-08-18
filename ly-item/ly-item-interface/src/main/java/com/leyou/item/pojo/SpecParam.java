package com.leyou.item.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "tb_spec_param")
@ApiModel("规格组下的参数实体类")
public class SpecParam {
        @Id
        @KeySql(useGeneratedKeys = true)
        @ApiModelProperty("规格组下的参数id,主键")
        private Long id;
        @ApiModelProperty("商品分类id")
        private Long cid;
         @ApiModelProperty("规格参数的组id")
        private Long groupId;
        @ApiModelProperty("参数名")
        private String name;
        @ApiModelProperty("是否是数字类型参数，true或false")
        @Column(name = "`numeric`")
        private Boolean numeric;
        @ApiModelProperty("数字类型参数的单位，非数字类型可以为空")
        private String unit;
        @ApiModelProperty("是否是sku通用属性，true或false")
        private Boolean generic;
        @ApiModelProperty("是否用于搜索过滤，true或false")
        private Boolean serching;
        @ApiModelProperty("数值类型参数，如果需要搜索\n" +
                "\t则添加分段间隔值，如cpu频率间隔0.5-1.0")
        private  String segments;


}
