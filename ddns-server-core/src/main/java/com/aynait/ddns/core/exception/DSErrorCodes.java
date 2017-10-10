package com.aynait.ddns.core.exception;

import lombok.Getter;

/**
 * Created by Tianya on 2017/9/30.
 */
@Getter
public enum DSErrorCodes {

    /**
     * 系统校验
     */
    SYSTEM_ERROR(10000, "服务器开小差，请稍后重试"),

    /**
     * 文件校验
     */
    FILE_NOT_EXISTS(20000, "文件不存在"),

    /**
     * 参数校验
     */
    RECORD_DOMAIN_ERROR(30000, "域名不能为空"),
    RECORD_IP_ERROR(30001, "IP地址不能为空"),
    RECORD_TOKEN_ERROR(30002, "Token校验失败"),;

    private int code;

    private String message;

    DSErrorCodes(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
