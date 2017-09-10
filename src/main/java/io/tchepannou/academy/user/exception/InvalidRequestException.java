package io.tchepannou.academy.user.exception;

public class InvalidRequestException extends BusinessException{
    public InvalidRequestException(final BusinessError errorCode) {
        super(errorCode);
    }
}
