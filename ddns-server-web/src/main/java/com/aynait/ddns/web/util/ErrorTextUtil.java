package com.aynait.ddns.web.util;

import com.aynait.ddns.core.common.DSConstant;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by Tianya on 2017/10/10.
 */
public class ErrorTextUtil {

    private static final String UNKNOWN_MESSAGE = "无法获取详细错误信息";

    /**
     * 获取异常中的主要错误信息，只获取\n前第一行的错误，如果有\r则多行显示
     */
    public static String getErrorText(Exception e) {
        try {
            String exceptionName = e.getClass().getSimpleName();
            String exceptionMessage = normalizeCause(e);
            return exceptionName.concat(DSConstant.SEPARATOR_LINE_FEED).concat(exceptionMessage).trim();
        } catch (Exception ignored) {
            return UNKNOWN_MESSAGE;
        }
    }

    private static String normalizeCause(Throwable throwable) {
        if (throwable == null) {
            return StringUtils.EMPTY;
        }

        String mainMessage = normalizeMessage(throwable.getMessage());
        Throwable cause = throwable.getCause();
        String causeMessage = normalizeCause(cause);

        return mainMessage.concat(DSConstant.SEPARATOR_LINE_FEED).concat(causeMessage).trim();
    }

    private static String normalizeMessage(String message) {
        if (StringUtils.isBlank(message)) {
            return StringUtils.EMPTY;
        }
        if (message.contains(DSConstant.SEPARATOR_LINE_FEED)) {
            message = message.split(DSConstant.SEPARATOR_LINE_FEED)[0].trim();
        }
        if (message.contains(DSConstant.SEPARATOR_CARRIAGE_RETURN)) {
            String[] messageList = message.split(DSConstant.SEPARATOR_CARRIAGE_RETURN);
            message = StringUtils.join(messageList, DSConstant.SEPARATOR_LINE_FEED);
        }
        return message.trim();
    }
}
