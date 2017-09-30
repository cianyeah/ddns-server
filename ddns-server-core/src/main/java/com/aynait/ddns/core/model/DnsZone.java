package com.aynait.ddns.core.model;

import com.aynait.ddns.core.common.DSConstant;
import lombok.Data;

import java.util.List;

/**
 * Created by Tianya on 2017/9/30.
 */
@Data
public class DnsZone {

    /**
     * TTL
     */
    private String ttl = DSConstant.DNS_TTL;

    /**
     * SOA
     */
    private String soa = DSConstant.DNS_SOA;

    /**
     * A记录
     */
    private List<ARecord> aRecordList;

    @Data
    public static class ARecord {

        /**
         * 域名
         */
        private String domain;

        /**
         * IP地址
         */
        private String ip;

        @Override
        public String toString() {
            return domain + DSConstant.DNS_A + ip;
        }
    }
}
