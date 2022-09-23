package com.ccut.dachuang.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("统一传递参数")
@Data
public class CommonResponse<T> {

    @ApiModelProperty("状态码说明")
    private String message;
    @ApiModelProperty("状态码")
    private String code;
    @ApiModelProperty("存储不同类型的参数返回前端")
    private T data;

    public CommonResponse(String message,  String code,T data) {
        this.message = message;
        this.code = code;
        this.data = data;
    }

}
