package com.aynait.ddns.web.vo;

import lombok.Data;

/**
 * Created by Tianya on 2017/10/10.
 */
@Data
public class UpdateARecordVO {

    /**
     * 域名
     */
    private String domain;

    /**
     * IP地址
     */
    private String ip;

    /**
     * Token校验
     */
    private String token;
}
