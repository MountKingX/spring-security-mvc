package com.kangmin.security.config;

import com.kangmin.security.model.Account;
import com.kangmin.security.service.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import static com.kangmin.security.model.security.WebUserRole.ADMIN;
import static com.kangmin.security.model.security.WebUserRole.NORMAL;
import static com.kangmin.security.model.security.WebUserRole.SUPER_ADMIN;

@Component
public class StaticDataLoader implements CommandLineRunner {

    private final AccountService accountService;
    private final PasswordEncoder passwordEncoder;

    public StaticDataLoader(final AccountService accountService,
                            final PasswordEncoder passwordEncoder) {
        this.accountService = accountService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(final String... strings) {
        if (!accountService.getAccountByUsername("dev").isPresent()) {
            accountService.createAccount(
                    new Account(
                            "id-0000",
                            "dev",
                            passwordEncoder.encode("dev"),
                            SUPER_ADMIN.name()
                    )
            );
            accountService.createAccount(
                    new Account(
                            "id-0001",
                            "sa",
                            passwordEncoder.encode("password"),
                            SUPER_ADMIN.name()
                    )
            );
            accountService.createAccount(
                    new Account(
                            "id-0002",
                            "normal",
                            passwordEncoder.encode("normal"),
                            NORMAL.name()
                    )
            );
            accountService.createAccount(
                    new Account(
                            "id-0003",
                            "admin",
                            passwordEncoder.encode("admin"),
                            ADMIN.name()
                    )
            );
        }
    }
}
