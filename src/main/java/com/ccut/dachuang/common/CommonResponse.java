package com.ccut.dachuang.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("统一传递参数")
public class CommonResponse<T> {

    @ApiModelProperty("状态码说明")
    private String message;
    @ApiModelProperty("存储不同类型的参数返回前端")
    private T data;
    @ApiModelProperty("状态码")
    private String code;

    public CommonResponse(String message, T data, String code) {
        this.message = message;
        this.data = data;
        this.code = code;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
