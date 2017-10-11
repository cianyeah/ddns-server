package com.aynait.ddns.core.manager;

import com.aynait.ddns.core.exception.DSException;
import com.aynait.ddns.core.model.DnsARecord;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Set;

/**
 * Created by Tianya on 2017/10/11.
 */
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
        //停止DNS服务
        super.stopNamed();

        while (true) {
            try {
                //获取最新的DNS记录
                Set<DnsARecord> dnsARecordSet = fillNewARecord(domain, ip);
                if (!CollectionUtils.isEmpty(dnsARecordSet)) {
                    //写入DNS记录
                    dnsWriteManager.writeARecord(dnsARecordSet);
                }
                break;
            } catch (DSException dsE) {
                dsE.printStackTrace();
                //未获取到文件锁，等待重试
                Thread.sleep(3000L);
            }
        }

        //启动DNS服务
        super.startNamed();
    }

    /**
     * 删除指定的DNS记录
     */
    private Set<DnsARecord> fillNewARecord(String domain, String ip) {
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
