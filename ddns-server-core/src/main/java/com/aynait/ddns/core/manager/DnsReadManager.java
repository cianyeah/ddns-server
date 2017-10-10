package com.aynait.ddns.core.manager;

import com.aynait.ddns.core.model.DnsARecord;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

import static com.aynait.ddns.core.common.DSConstant.*;

/**
 * Created by Tianya on 2017/10/9.
 */
@Service
public class DnsReadManager extends DnsManager {

    public Set<DnsARecord> readARecord() {
        super.checkFileExists();

        Set<DnsARecord> dnsARecordSet = new HashSet<>();
        try (
                FileInputStream fis = new FileInputStream(AYNAIT_COM_FILE);
                InputStreamReader isr = new InputStreamReader(fis, UTF8);
                BufferedReader br = new BufferedReader(isr)
        ) {
            String line;
            while ((line = br.readLine()) != null) {
                if (StringUtils.isBlank(line)) {
                    continue;
                }
                if (DNS_TTL.equals(line)) {
                    continue;
                }
                if (DNS_SOA.equals(line)) {
                    continue;
                }
                if (DNS_NS.equals(line)) {
                    continue;
                }
                if (line.contains(DNS_A)) {
                    String domain = line.split(DNS_A)[0];
                    String ip = line.split(DNS_A)[1];

                    DnsARecord dnsARecord = new DnsARecord();
                    dnsARecord.setDomain(domain);
                    dnsARecord.setIp(ip);
                    if (!dnsARecordSet.add(dnsARecord)) {
                        dnsARecordSet.remove(dnsARecord);
                        dnsARecordSet.add(dnsARecord);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dnsARecordSet;
    }
}
