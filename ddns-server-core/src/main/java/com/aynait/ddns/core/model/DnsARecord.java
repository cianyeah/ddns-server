package com.aynait.ddns.core.model;

import com.aynait.ddns.core.common.DSConstant;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by Tianya on 2017/10/9.
 */
@Data
public class DnsARecord {

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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DnsARecord that = (DnsARecord) o;
        return StringUtils.equals(domain, that.domain);
    }

    @Override
    public int hashCode() {
        return domain.hashCode();
    }
}
