package com.aynait.ddns.core.exception;

import lombok.Getter;

/**
 * Created by Tianya on 2017/9/30.
 */
@Getter
public enum DSErrorCodes {

    //系统校验
    SYSTEM_ERROR(10000, "服务器开小差，请稍后重试"),;

    private int code;

    private String message;

    DSErrorCodes(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
