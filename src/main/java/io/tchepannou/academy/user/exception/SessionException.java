package io.tchepannou.academy.user.exception;

public class SessionException extends BusinessException{
    public SessionException(final BusinessError errorCode) {
        super(errorCode);
    }
}
