package com.leyou.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum ExceptionEnums {

    BEAND_CANNOT_BE_FOND(404,"品牌不存在"),
    SPEC_GROUP_NOT_FOND(404,"商品规格组不存在"),
    SPEC_PARAM_NOT_FOND(404,"商品规格参数不存在"),
    GOODS_NOT_FOND(404,"商品不存在"),
    GOODS_DETAIL_NOT_FOND(404,"商品详情不存在"),
    GOODS_SKU_NOT_FOND(404,"商品的sku不存在"),
    GOODS_STOCK_NOT_FOND(404,"商品的库存不存在"),
    CATEGORY_NOT_BE_FOND(404,"商品分类不存在"),
    BEAND_SAVE_EORRR(500,"商品品牌保存失败"),
    UPLOAD_FILE_EORRR(500,"文件上传失败"),
    INSERT_GOODS_EORRR(500,"新增商品失败"),
    UPDATE_GOODS_EORRR(500,"修改商品失败"),
    FILE_TYPE_NOT_MATCH(400,"无效的文件类型"),
    GOODS_ID_NOT_BE_FON(400,"商品id不能为空"),
    ;
    private int code;
    private String msg;

}
