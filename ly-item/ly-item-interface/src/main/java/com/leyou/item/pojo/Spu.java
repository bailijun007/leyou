package com.leyou.item.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.*;
import java.util.Date;

@ApiModel("spu实体类，该实体类描述的是一个抽象性的商品")
@Data
@Table(name = "tb_spu")
public class Spu {
    @Id
   @KeySql(useGeneratedKeys = true)
    @ApiModelProperty("spu id,主键")
    private Long id;
    @ApiModelProperty("商品所属品牌id")
    private Long brandId;
    /**
     * 1级类目
     */
    @ApiModelProperty("1级类目id")
    private Long cid1;
    /**
     * 2级类目
     */
    @ApiModelProperty("2级类目id")
    private Long cid2;
    /**
     * 3级类目
     */
    @ApiModelProperty("3级类目id")
    private Long cid3;
    /**
     * 标题
     */
    @ApiModelProperty("标题")
    private String title;
    /**
     * 子标题
     */
    @ApiModelProperty("子标题")
    private String subTitle;
    /**
     * 是否上架
     */
    @ApiModelProperty("是否上架")
    private Boolean saleable;
    /**
     * 是否有效，逻辑删除使用
     */
    @JsonIgnore //不想返回的字段，需要添加@JsonIgnore注解
    @ApiModelProperty("是否有效，逻辑删除使用")
    private Boolean valid;
    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private Date createTime;
    /**
     * 最后修改时间
     */
    @JsonIgnore //不想返回的字段，需要添加@JsonIgnore注解
    @ApiModelProperty("最后修改时间")
    private Date lastUpdateTime;

 /**
  * 商品分类名称(新加字段)
  */
   @ApiModelProperty("商品分类名称")
   @Transient//不是数据库字段，需要加上@Transient注解
   private String cname;

  /**
   * 品牌名称(新加字段)
   */
  @ApiModelProperty("品牌名称")
  @Transient//不是数据库字段，需要加上@Transient注解
  private String bname;

}
