package com.aynait.ddns.web.vo;

import com.aynait.ddns.core.exception.DSErrorCodes;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Tianya on 2017/10/10.
 */
@Data
public class ResultVO<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private boolean success;

    private int errorCode;

    private String errorText;

    private T successInfo;

    private Map<String, Object> params;

    public static <T> ResultVO<T> newResult(boolean success) {
        ResultVO<T> resultVO = new ResultVO<>();
        resultVO.success = success;
        return resultVO;
    }

    public static <T> ResultVO<T> newErrorResult() {
        return newResult(false);
    }

    public static <T> ResultVO<T> newErrorResult(String errorText) {
        ResultVO<T> resultVO = newErrorResult();
        resultVO.errorText = errorText;
        return resultVO;
    }

    public static <T> ResultVO<T> newErrorResult(int errorCode, String errorText) {
        ResultVO<T> resultVO = newErrorResult();
        resultVO.errorCode = errorCode;
        resultVO.errorText = errorText;
        return resultVO;
    }

    public static <T> ResultVO<T> newErrorResult(DSErrorCodes dsErrorCodes) {
        ResultVO<T> resultVO = newErrorResult();
        resultVO.errorCode = dsErrorCodes.getCode();
        resultVO.errorText = dsErrorCodes.getMessage();
        return resultVO;
    }

    public static <T> ResultVO<T> newSuccessResult() {
        return newResult(true);
    }

    public static <T> ResultVO<T> newSuccessResult(T successInfo) {
        ResultVO<T> resultVO = newSuccessResult();
        resultVO.successInfo = successInfo;
        return resultVO;
    }

    public void addParam(String key, Object value) {
        if (params == null) {
            params = new HashMap<>();
        }
        params.put(key, value);
    }
}
