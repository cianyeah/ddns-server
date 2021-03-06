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
    FILE_LOCK_ERROR(20001, "文件锁定失败"),

    /**
     * 参数校验
     */
    RECORD_DOMAIN_ERROR(30000, "域名不能为空"),
    RECORD_IP_ERROR(30001, "IP地址不合法"),
    RECORD_TOKEN_ERROR(30002, "Token校验失败"),

    /**
     * DNS校验
     */
    STOP_NAMED_ERROR(40000, "停止DNS服务失败"),
    START_NAMED_ERROR(40001, "启动DNS服务失败"),
    DNS_RECORD_EXISTS(40002, "DNS记录已存在"),;

    private int code;

    private String message;

    DSErrorCodes(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
