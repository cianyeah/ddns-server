package com.aynait.ddns.core.manager;

import com.alibaba.fastjson.JSON;
import com.aynait.ddns.core.exception.DSErrorCodes;
import com.aynait.ddns.core.exception.DSException;
import com.aynait.ddns.core.model.DnsARecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.channels.FileLock;
import java.util.Set;

import static com.aynait.ddns.core.common.DSConstant.*;

/**
 * Created by Tianya on 2017/10/11.
 */
@Slf4j
@Service
public class DnsWriteManager extends DnsManager {

    /**
     * 写入DNS记录
     */
    void writeARecord(Set<DnsARecord> dnsARecordSet) throws Exception {
        log.warn("DnsWriteManager.writeARecord dnsARecordSet:{}", JSON.toJSONString(dnsARecordSet));

        //检查文件是否存在
        super.checkFileExists();

        try (
                FileOutputStream fos = new FileOutputStream(AYNAIT_COM_FILE);
                OutputStreamWriter osw = new OutputStreamWriter(fos, UTF8);
                BufferedWriter bw = new BufferedWriter(osw);
                //获取文件锁
                FileLock fl = fos.getChannel().tryLock()
        ) {
            if (fl == null) {
                throw new DSException(DSErrorCodes.FILE_LOCK_ERROR);
            }

            this.writeToFile(bw, dnsARecordSet);
        }
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
}
