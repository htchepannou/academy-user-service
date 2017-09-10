package io.tchepannou.academy.user.service;

import io.tchepannou.academy.user.domain.Account;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import java.io.UnsupportedEncodingException;

@Component
public class PasswordEncryptor {
    private static final String SALT_PREFIX = "RandomSalt$3094309re!dflkfd$";

    public String encrypt (final Account account, String password){
        if (password == null){
            throw new IllegalArgumentException("password is null");
        }
        return md5(account, password);
    }

    public boolean matches(final Account account, String password){
        final String encrypted = md5(account, password);
        return encrypted.equals(account.getPassword());
    }

    private String md5(Account account, final String password){
        try {

            final String text = String.format("%s-%s", salt(account), password);
            return DigestUtils.md5DigestAsHex(text.getBytes("utf-8"));

        } catch (UnsupportedEncodingException e){
            throw new IllegalStateException("Unable to encrypt password", e);
        }
    }

    private String salt(Account account){
        return String.format("%s,%s,%s", SALT_PREFIX, account.getId(), account.getCreationDateTime().getTime());
    }
}
