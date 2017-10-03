package io.tchepannou.academy.user.exception;

public enum BusinessError {
    ACCOUNT_NOT_FOUND("ACCOUNT_NOT_FOUND", "Account not found"),
    INVALID_ROLE("INVALID_ROLE", "Role is not valid"),
    PASSWORD_MISMATCH("PASSWORD_MISMATCH", "Password doesn't match"),
    PERSON_NOT_FOUND("PERSON_NOT_FOUND", "Person not found"),
    ROLE_NOT_FOUND("ROLE_NOT_FOUND", "Role not found"),
    SESSION_EXPIRED("SESSION_EXPIRED", "Session has expired"),
    SESSION_INACTIVE("SESSION_INACTIVE", "Session is no longer active"),
    SESSION_NOT_FOUND("SESSION_NOT_FOUND", "Session Not Found"),
    BAD_REQUEST("BAD_REQUEST", "Bad Request")
    ;

    private String code;
    private String description;

    BusinessError(String code, String description){
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
