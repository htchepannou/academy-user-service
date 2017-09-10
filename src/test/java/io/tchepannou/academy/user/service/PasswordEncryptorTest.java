package io.tchepannou.academy.user.service;

import io.tchepannou.academy.user.domain.Account;
import org.junit.Test;

import java.sql.Timestamp;

import static org.assertj.core.api.Assertions.assertThat;

public class PasswordEncryptorTest {
    PasswordEncryptor encryptor = new PasswordEncryptor();

    @Test
    public void shouldEncyptPassword() throws Exception {
        final Account account = createAccount(1, 1111l);

        assertThat(encryptor.encrypt(account, "foo")).isEqualTo("dbfe1cffb708eb3b316e2f68a1ff2836");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenEncryptingNullPassowrd() throws Exception {
        final Account account = createAccount(1, 12L);

        encryptor.encrypt(account, null);
    }

    @Test
    public void matches() throws Exception {
        final Account account = createAccount(1, 1111l, "dbfe1cffb708eb3b316e2f68a1ff2836");

        assertThat(encryptor.matches(account, "foo")).isTrue();
    }

    private Account createAccount(final Integer id, final Long time){
        return createAccount(id, time, null);
    }

    private Account createAccount(final Integer id, final Long time, final String password){
        final Account account = new Account();
        account.setId(id);
        account.setCreationDateTime(new Timestamp(time));
        account.setPassword(password);
        return account;
    }
}
