package io.tchepannou.academy.user.service;

import io.tchepannou.academy.user.dao.AccountDao;
import io.tchepannou.academy.user.dao.PersonDao;
import io.tchepannou.academy.user.dao.RoleDao;
import io.tchepannou.academy.user.dao.RoleTypeDao;
import io.tchepannou.academy.user.dao.SessionDao;
import io.tchepannou.academy.user.domain.Account;
import io.tchepannou.academy.user.domain.Person;
import io.tchepannou.academy.user.domain.Role;
import io.tchepannou.academy.user.domain.RoleType;
import io.tchepannou.academy.user.domain.Session;
import io.tchepannou.academy.user.client.auth.AuthRequest;
import io.tchepannou.academy.user.client.auth.AuthResponse;
import io.tchepannou.academy.user.exception.BusinessError;
import io.tchepannou.academy.user.exception.BusinessException;
import io.tchepannou.academy.user.exception.InvalidRequestException;
import io.tchepannou.academy.user.exception.NotFoundException;
import io.tchepannou.academy.user.exception.SessionException;
import io.tchepannou.academy.user.mapper.SessionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.UUID;

@Component
@ConfigurationProperties("academy.service.AuthService")
public class AuthService {
    @Autowired
    private AccountDao accountDao;

    @Autowired
    private PersonDao personDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private RoleTypeDao roleTypeDao;

    @Autowired
    private SessionDao sessionDao;

    @Autowired
    private PasswordEncryptor encryptor;

    @Autowired
    private SessionMapper sessionMapper;

    private int sessionTTLMinutes;

    @Transactional
    public AuthResponse login(final AuthRequest request){
        final RoleType roleType = roleTypeDao.findByNameIgnoreCase(request.getRole());
        if (roleType == null){
            throw new InvalidRequestException(BusinessError.INVALID_ROLE);
        }

        final Person person = personDao.findByEmailIgnoreCase(request.getEmail());
        if (person == null){
            throw new BusinessException(BusinessError.PERSON_NOT_FOUND);
        }

        final Role role = roleDao.findByPersonIdAndTypeId(person.getId(), roleType.getId());
        if (role == null){
            throw new BusinessException(BusinessError.ROLE_NOT_FOUND);
        }

        final Account account = accountDao.findByPersonId(person.getId());
        if (account == null){
            throw new BusinessException(BusinessError.ACCOUNT_NOT_FOUND);
        } else if (!encryptor.matches(account, request.getPassword())){
            throw new BusinessException(BusinessError.PASSWORD_MISMATCH);
        }

        final long now = System.currentTimeMillis();
        final Session session = new Session();
        session.setAccessToken(UUID.randomUUID().toString());
        session.setAccountId(account.getId());
        session.setRoleId(roleType.getId());
        session.setExpiryDateTime(new Timestamp(now + sessionTTLMinutes*60*1000));
        session.setActive(true);

        sessionDao.save(session);

        final AuthResponse response = new AuthResponse();
        response.setSession(sessionMapper.toSessionDto(session));
        return response;
    }

    public AuthResponse validate(final String accessToken){
        final Session session = sessionDao.findByAccessToken(accessToken);
        if (session == null){
            throw new NotFoundException(BusinessError.SESSION_NOT_FOUND);
        }

        /* session expired ? */
        final long now = System.currentTimeMillis();
        if (now > session.getExpiryDateTime().getTime()){
            throw new SessionException(BusinessError.SESSION_EXPIRED);
        }

        /* session active ? */
        if (!session.isActive()){
            throw new SessionException(BusinessError.SESSION_INACTIVE);
        }

        final AuthResponse response = new AuthResponse();
        response.setSession(sessionMapper.toSessionDto(session));
        return response;
    }

    public int getSessionTTLMinutes() {
        return sessionTTLMinutes;
    }

    public void setSessionTTLMinutes(final int sessionTTLMinutes) {
        this.sessionTTLMinutes = sessionTTLMinutes;
    }
}
