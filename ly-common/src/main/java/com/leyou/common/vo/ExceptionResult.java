package com.leyou.common.vo;

import com.leyou.common.enums.ExceptionEnums;
import lombok.Data;

//vo : view object
@Data
public class ExceptionResult {
    private String message;
    private int code;
    private Long timestamp;

    public ExceptionResult(ExceptionEnums enums){
        this.code=enums.getCode();
        this.message=enums.getMsg();
        this.timestamp=System.currentTimeMillis();

    }

}
