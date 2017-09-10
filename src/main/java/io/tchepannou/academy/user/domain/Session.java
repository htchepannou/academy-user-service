package io.tchepannou.academy.user.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table( name = "T_SESSION")
public class Session extends Persistent{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name="account_fk")
    private Integer accountId;

    @Column(name="role_fk")
    private Integer roleId;

    @Column(name="access_token")
    private String accessToken;

    @Column(name="expiry_datetime")
    private Timestamp expiryDateTime;

    private boolean active;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(final Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(final Integer roleId) {
        this.roleId = roleId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(final String accessToken) {
        this.accessToken = accessToken;
    }

    public Timestamp getExpiryDateTime() {
        return expiryDateTime;
    }

    public void setExpiryDateTime(final Timestamp expiryDateTime) {
        this.expiryDateTime = expiryDateTime;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(final boolean active) {
        this.active = active;
    }
}
