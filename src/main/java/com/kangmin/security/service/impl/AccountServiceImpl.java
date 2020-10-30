package com.kangmin.security.service.impl;

import com.kangmin.security.dao.AccountDao;
import com.kangmin.security.model.Account;
import com.kangmin.security.service.AccountService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountDao accountDao;

    public AccountServiceImpl(final AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public Page<Account> getAll(final Pageable pageable) {
        return accountDao.findAll(pageable).map(each -> {
            each.setPassword("*******");
            return each;
        });
    }
//    @Override
//    public Optional<List<Account>> getAll() {
//        return Optional.of(accountDao.findAll());
//    }

    @Override
    public Optional<Account> createAccount(final Account account) {
        if (accountDao.findAccountByUsername(account.getUsername()).isPresent()) {
            return Optional.empty();
        }
        accountDao.save(account);
        return Optional.of(account);
    }

    @Override
    public Optional<Account> getAccountByUsername(final String username) {
        return accountDao.findAccountByUsername(username);
    }

    @Override
    public Optional<Account> getAccountById(final String accountId) {
        return accountDao.findAccountByAccountId(accountId);
    }
}
