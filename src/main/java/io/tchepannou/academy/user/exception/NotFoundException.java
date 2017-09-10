package io.tchepannou.academy.user.exception;

public class NotFoundException extends BusinessException{
    public NotFoundException(final BusinessError errorCode) {
        super(errorCode);
    }
}
