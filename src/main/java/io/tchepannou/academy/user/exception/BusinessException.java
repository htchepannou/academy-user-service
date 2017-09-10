package io.tchepannou.academy.user.exception;

public class BusinessException extends RuntimeException {
    private BusinessError errorCode;

    public BusinessException(final BusinessError errorCode) {
        this.errorCode = errorCode;
    }

    public BusinessError getErrorCode() {
        return errorCode;
    }

    @Override
    public String getMessage() {
        return errorCode.getCode() + " - " + errorCode.getDescription();
    }
}
