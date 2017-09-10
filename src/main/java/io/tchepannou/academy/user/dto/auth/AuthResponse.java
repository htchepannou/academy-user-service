package io.tchepannou.academy.user.dto.auth;

import io.tchepannou.academy.user.dto.BaseResponse;

public class AuthResponse extends BaseResponse{
    private SessionDto session;

    public SessionDto getSession() {
        return session;
    }

    public void setSession(final SessionDto session) {
        this.session = session;
    }
}
