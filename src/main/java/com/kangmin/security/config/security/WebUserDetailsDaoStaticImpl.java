package com.kangmin.security.config.security;

import com.kangmin.security.dao.AccountDao;
import com.kangmin.security.model.Account;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

import static com.kangmin.security.model.security.WebUserRole.ADMIN;
import static com.kangmin.security.model.security.WebUserRole.NORMAL;
import static com.kangmin.security.model.security.WebUserRole.SUPER_ADMIN;
import static com.kangmin.security.model.security.WebUserRole.UNKNOWN;

@Import({
        PasswordConfig.class,
})
@Repository("jpa")
public class WebUserDetailsDaoStaticImpl implements WebUserDetailsDao {

    private final AccountDao accountDao;

    public WebUserDetailsDaoStaticImpl(final AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public Optional<WebUserDetails> getUserDetailsByUsername(final String username) {

        final Optional<Account> accountOpt = accountDao.findAccountByUsername(username);
        if (accountOpt.isPresent()) {
            final Account account = accountOpt.get();
            return Optional.of(mapAccountToWebUserDetails(account));
        }

        return Optional.empty();
    }

    private WebUserDetails mapAccountToWebUserDetails(final Account account) {
        return new WebUserDetails(
                account.getUsername(),
                account.getPassword(),
                getAuthorities(account.getRole()),
                true,
                true,
                true,
                true
        );
    }

    private Set<SimpleGrantedAuthority> getAuthorities(final String role) {
        switch (role) {
            case "NORMAL":
                return NORMAL.getGrantedAuthorities();
            case "ADMIN":
                return ADMIN.getGrantedAuthorities();
            case "SUPER_ADMIN":
                return SUPER_ADMIN.getGrantedAuthorities();
            default:
                return UNKNOWN.getGrantedAuthorities();
        }
    }
}
