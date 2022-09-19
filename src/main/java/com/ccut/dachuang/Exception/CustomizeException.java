package com.ccut.dachuang.Exception;

import com.ccut.dachuang.common.ErrorEnum;


public class CustomizeException extends RuntimeException{

    private ErrorEnum error;

    public CustomizeException(ErrorEnum error) {
        this.error = error;
    }

    public ErrorEnum getError() {
        return error;
    }
}
