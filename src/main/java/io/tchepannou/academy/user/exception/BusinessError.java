package io.tchepannou.academy.user.exception;

public enum BusinessError {
    PERSON_NOT_FOUND("PERSON_NOT_FOUND", "Person not found"),
    ACCOUNT_NOT_FOUND("PERSON_NOT_FOUND", "Person not found"),
    PASWORD_MISMATCH("PASWORD_MISMATCH", "Password doesn't match"),
    ROLE_NOT_FOUND("ROLE_NOT_FOUND", "Role not found"),
    INVALID_ROLE("INVALID_ROLE", "Role is not valid"),
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
