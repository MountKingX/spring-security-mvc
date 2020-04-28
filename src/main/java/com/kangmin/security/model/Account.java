package com.kangmin.security.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "account")
public final class Account {

    private String accountId;

    @Id
    private String username;

    private String password;

    private String role;

    public Account() {

    }

    public Account(final String accountId,
                   final String username,
                   final String password,
                   final String role) {
        this.accountId = accountId;
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
