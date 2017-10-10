package com.aynait.ddns.core.manager;

import com.aynait.ddns.core.exception.DSErrorCodes;
import com.aynait.ddns.core.exception.DSException;

import java.io.File;

import static com.aynait.ddns.core.common.DSConstant.AYNAIT_COM_FILE;

/**
 * Created by Tianya on 2017/10/10.
 */
public abstract class DnsManager {

    /**
     * 检查文件是否存在
     */
    protected void checkFileExists() {
        File file = new File(AYNAIT_COM_FILE);
        if (!file.exists()) {
            throw new DSException(DSErrorCodes.FILE_NOT_EXISTS);
        }
    }
}
