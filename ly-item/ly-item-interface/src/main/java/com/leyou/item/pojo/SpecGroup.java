package com.leyou.item.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

@ApiModel("规格参数实体类")
@Data
@Table(name = "tb_spec_group")
public class SpecGroup {
    @Id
    @KeySql(useGeneratedKeys = true)
    @ApiModelProperty("规格参数的id,主键")
    private Long id;
    @ApiModelProperty("商品分类id,一个分类下有多个规格组")
    private Long cid;
    @ApiModelProperty("规格组的名称")
    private String name;
}
