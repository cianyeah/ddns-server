package com.aynait.ddns.web.util;

import com.aynait.ddns.core.common.DSConstant;
import org.apache.commons.lang3.StringUtils;
import sun.net.util.IPAddressUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Created by Tianya on 2017/10/10.
 */
public class RequestValidator {

    private static final int SECOND_OFFSET = 10;

    /**
     * IP地址校验
     */
    public static boolean checkIp(String ip) {
        if (StringUtils.isBlank(ip)) {
            return false;
        }
        if (!IPAddressUtil.isIPv4LiteralAddress(ip)) {
            return false;
        }
        byte[] addr = IPAddressUtil.textToNumericFormatV4(ip);
        return internalIp(addr);
    }

    /**
     * 是否为内网域名
     */
    private static boolean internalIp(byte[] addr) {
        final byte b0 = addr[0];
        final byte b1 = addr[1];
        //10.x.x.x/8
        final byte SECTION_1 = 0x0A;
        //172.16.x.x/12
        final byte SECTION_2 = (byte) 0xAC;
        final byte SECTION_3 = (byte) 0x10;
        final byte SECTION_4 = (byte) 0x1F;
        //192.168.x.x/16
        final byte SECTION_5 = (byte) 0xC0;
        final byte SECTION_6 = (byte) 0xA8;
        switch (b0) {
            case SECTION_1:
                return true;
            case SECTION_2:
                if (b1 >= SECTION_3 && b1 <= SECTION_4) {
                    return true;
                }
            case SECTION_5:
                switch (b1) {
                    case SECTION_6:
                        return true;
                }
            default:
                return false;
        }
    }

    /**
     * Token校验
     */
    public static boolean checkToken(String token) {
        if (StringUtils.isBlank(token)) {
            return false;
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DSConstant.DATE_FORMAT, Locale.CHINA);
            LocalDateTime tokenTime = LocalDateTime.parse(token, formatter);
            LocalDateTime time = LocalDateTime.now();
            boolean leftOffset = tokenTime.plusSeconds(SECOND_OFFSET).isAfter(time);
            boolean rightOffset = tokenTime.minusSeconds(SECOND_OFFSET).isBefore(time);
            return leftOffset && rightOffset;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
