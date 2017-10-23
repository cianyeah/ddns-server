package com.aynait.ddns.core.manager;

import com.aynait.ddns.core.exception.DSErrorCodes;
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
     * 停止DNS服务
     */
    void stopNamed() {
        try {
            Process process = Runtime.getRuntime().exec(EXEC_STOP_NAMED);
            process.waitFor();
        } catch (Exception e) {
            log.error("DnsManager.stopNamed throw Exception", e);
        }
    }

    /**
     * 启动DNS服务
     */
    void startNamed() {
        try {
            Process process = Runtime.getRuntime().exec(EXEC_START_NAMED);
            process.waitFor();
        } catch (Exception e) {
            log.error("DnsManager.startNamed throw Exception", e);
        }
    }
}
