package com.aynait.ddns.core.manager;

import com.aynait.ddns.core.model.DnsARecord;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.channels.FileLock;
import java.util.Set;

import static com.aynait.ddns.core.common.DSConstant.*;

/**
 * Created by Tianya on 2017/9/30.
 */
@Service
public class DnsAddManager extends DnsManager {

    @Resource
    private DnsReadManager dnsReadManager;

    public void addARecord(String domain, String ip) {
        super.checkFileExists();

        stopNamed();

        Set<DnsARecord> dnsARecordSet = fillNewARecord(domain, ip);
        try (
                FileOutputStream fos = new FileOutputStream(AYNAIT_COM_FILE);
                OutputStreamWriter osw = new OutputStreamWriter(fos, UTF8);
                BufferedWriter bw = new BufferedWriter(osw);
                FileLock fl = fos.getChannel().tryLock()
        ) {
            if (fl == null) {
                Thread.sleep(3000L);
                addARecord(domain, ip);
                return;
            }

            writeToFile(bw, dnsARecordSet);
        } catch (Exception e) {
            e.printStackTrace();
        }

        startNamed();
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

    /**
     * 写入DNS记录到文件
     */
    private void writeToFile(BufferedWriter bw, Set<DnsARecord> dnsARecordSet) throws IOException {
        bw.write(DNS_TTL);
        bw.newLine();
        bw.write(DNS_SOA);
        bw.newLine();
        bw.write(DNS_NS);
        bw.newLine();
        for (DnsARecord aRecord : dnsARecordSet) {
            bw.write(aRecord.toString());
            bw.newLine();
        }
    }

    /**
     * 停止DNS服务
     */
    private void stopNamed() {
        try {
            Process process = Runtime.getRuntime().exec(EXEC_STOP_NAMED);
            process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 启动DNS服务
     */
    private void startNamed() {
        try {
            Process process = Runtime.getRuntime().exec(EXEC_START_NAMED);
            process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
