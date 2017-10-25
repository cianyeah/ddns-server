package com.aynait.ddns.core.manager;

import com.aynait.ddns.core.exception.DSException;
import com.aynait.ddns.core.model.DnsARecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Set;

/**
 * Created by Tianya on 2017/10/11.
 */
@Slf4j
@Service
public class DnsDeleteManager extends DnsManager {

    @Resource
    private DnsReadManager dnsReadManager;

    @Resource
    private DnsWriteManager dnsWriteManager;

    /**
     * 删除DNS记录
     */
    public void deleteARecord(String domain, String ip) throws Exception {
        log.warn("DnsDeleteManager.deleteARecord domain:{} ip:{}", domain, ip);

        while (true) {
            try {
                //获取最新的DNS记录
                Set<DnsARecord> dnsARecordSet = this.fillNewARecord(domain, ip);
                //写入DNS记录
                dnsWriteManager.writeARecord(dnsARecordSet);
                break;
            } catch (DSException dsE) {
                log.error("DnsDeleteManager.deleteARecord throw Exception", dsE);
                //未获取到文件锁，等待重试
                Thread.sleep(3000L);
            }
        }

        //重启DNS服务
        super.restartNamed();
    }

    /**
     * 删除指定的DNS记录
     */
    private Set<DnsARecord> fillNewARecord(String domain, String ip) throws Exception {
        DnsARecord dnsARecord = new DnsARecord();
        dnsARecord.setDomain(domain);
        dnsARecord.setIp(ip);

        Set<DnsARecord> dnsARecordSet = dnsReadManager.readARecord();
        if (!dnsARecordSet.contains(dnsARecord)) {
            return Collections.emptySet();
        }
        dnsARecordSet.remove(dnsARecord);

        return dnsARecordSet;
    }
}
