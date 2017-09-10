package io.tchepannou.academy.user.mapper;

import io.tchepannou.academy.user.domain.Session;
import io.tchepannou.academy.user.dto.auth.SessionDto;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class SessionMapper {
    public SessionDto toSessionDto(final Session session){
        final SessionDto dto = new SessionDto();
        dto.setAccessToken(session.getAccessToken());
        dto.setAccountId(session.getAccountId());
        dto.setExpiryDateTime(new Date(session.getExpiryDateTime().getTime()));
        dto.setRoleId(session.getRoleId());
        dto.setActive(session.isActive());
        dto.setId(session.getId());
        return dto;
    }
}
