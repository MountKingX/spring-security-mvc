package com.kangmin.security.service;

import com.kangmin.security.model.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface AccountService {

    Page<Account> getAll(Pageable pageable);
    // Optional<List<Account>> getAll();

    Optional<Account> createAccount(final Account account);

    Optional<Account> getAccountByUsername(final String username);

    Optional<Account> getAccountById(final String accountId);
}
