package com.aynait.ddns.core.manager;

import com.aynait.ddns.core.exception.DSErrorCodes;
import com.aynait.ddns.core.exception.DSException;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;

import static com.aynait.ddns.core.common.DSConstant.*;

/**
 * Created by Tianya on 2017/10/10.
 */
@Slf4j
public class DnsManager {

    /**
     * 检查文件是否存在
     */
    void checkFileExists() throws IOException {
        File file = new File(AYNAIT_COM_FILE);
        if (!file.exists()) {
            throw new IOException(DSErrorCodes.FILE_NOT_EXISTS.getMessage());
        }
    }

    /**
     * 重启DNS服务
     */
    void restartNamed() {
        this.stopNamed();
        this.startNamed();
    }

    /**
     * 停止DNS服务
     */
    private void stopNamed() {
        log.info("DnsManager.stopNamed stopping named service");
        try {
            Process process = Runtime.getRuntime().exec(EXEC_STOP_NAMED);
            process.waitFor();
        } catch (Exception e) {
            log.error("DnsManager.stopNamed throw Exception", e);
            throw new DSException(DSErrorCodes.STOP_NAMED_ERROR);
        }
        log.info("DnsManager.stopNamed named service stopped");
    }

    /**
     * 启动DNS服务
     */
    private void startNamed() {
        log.info("DnsManager.startNamed starting named service");
        try {
            Process process = Runtime.getRuntime().exec(EXEC_START_NAMED);
            process.waitFor();
        } catch (Exception e) {
            log.error("DnsManager.startNamed throw Exception", e);
            throw new DSException(DSErrorCodes.START_NAMED_ERROR);
        }
        log.info("DnsManager.startNamed named service started");
    }
}
