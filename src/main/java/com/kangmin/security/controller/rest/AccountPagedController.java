package com.kangmin.security.controller.rest;

import com.kangmin.security.model.Account;
import com.kangmin.security.service.AccountService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v2/accounts")
public class AccountPagedController {

    private final AccountService accountService;

    public AccountPagedController(final AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping({"", "/"})
    public Page<Account> getPagedAccountsList(
        final @PageableDefault(
            size = 3, sort = {"accountId"}, direction = Sort.Direction.DESC
        ) Pageable pageable
    ) {
        return accountService.getAll(pageable);
    }
}
