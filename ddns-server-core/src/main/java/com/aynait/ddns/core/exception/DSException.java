package com.aynait.ddns.core.exception;

import lombok.Getter;

/**
 * Created by Tianya on 2017/9/30.
 */
@Getter
public class DSException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private int errorCode;

    private String errorMessage;

    public DSException(String errorMessage) {
        super();
        this.errorCode = -1;
        this.errorMessage = errorMessage;
    }

    public DSException(int errorCode, String errorMessage) {
        super();
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public DSException(DSErrorCodes dsErrorCodes) {
        super();
        this.errorCode = dsErrorCodes.getCode();
        this.errorMessage = dsErrorCodes.getMessage();
    }

    @Override
    public String getMessage() {
        return errorMessage;
    }
}
