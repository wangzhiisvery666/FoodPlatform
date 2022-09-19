package com.ccut.dachuang.common;

public class ResponseTools {

    public static <T> CommonResponse<T> success(T data){

        return new CommonResponse<T>("成功",data,"200");
    }
    public static <T> CommonResponse<T> error(){

        return new CommonResponse<T>("成功",null,"200");
    }
}
