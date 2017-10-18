package com.aynait.ddns.core.manager;

import com.aynait.ddns.core.exception.DSException;
import com.aynait.ddns.core.model.DnsARecord;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Set;

/**
 * Created by Tianya on 2017/9/30.
 */
@Service
public class DnsAddManager extends DnsManager {

    @Resource
    private DnsReadManager dnsReadManager;

    @Resource
    private DnsWriteManager dnsWriteManager;

    /**
     * 添加DNS记录
     */
    public void addARecord(String domain, String ip) throws Exception {
        //停止DNS服务
        super.stopNamed();

        while (true) {
            try {
                //获取最新的DNS记录
                Set<DnsARecord> dnsARecordSet = this.fillNewARecord(domain, ip);
                //写入DNS记录
                dnsWriteManager.writeARecord(dnsARecordSet);
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
     * 添加新的DNS记录
     */
    private Set<DnsARecord> fillNewARecord(String domain, String ip) {
        DnsARecord dnsARecord = new DnsARecord();
        dnsARecord.setDomain(domain);
        dnsARecord.setIp(ip);

        Set<DnsARecord> dnsARecordSet = dnsReadManager.readARecord();
        if (!dnsARecordSet.add(dnsARecord)) {
            dnsARecordSet.remove(dnsARecord);
            dnsARecordSet.add(dnsARecord);
        }

        return dnsARecordSet;
    }
}
