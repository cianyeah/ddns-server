package com.aynait.ddns.web.util;

import com.aynait.ddns.core.common.DSConstant;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Created by Tianya on 2017/10/10.
 */
public class TokenValidator {

    private static final int SECOND_OFFSET = 10;

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
