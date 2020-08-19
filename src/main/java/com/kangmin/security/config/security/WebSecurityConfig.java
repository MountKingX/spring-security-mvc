package com.kangmin.security.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.kangmin.security.model.security.WebUserRole.ADMIN;
import static com.kangmin.security.model.security.WebUserRole.SUPER_ADMIN;

@Import({
        PasswordConfig.class,
})
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService webUserDetailsService;

    public WebSecurityConfig(final PasswordEncoder passwordEncoder,
                             final UserDetailsService webUserDetailsService) {
        this.passwordEncoder = passwordEncoder;
        this.webUserDetailsService = webUserDetailsService;
    }

    @Override
    protected void configure(final HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .authorizeRequests()
                .antMatchers(
                        "/", "index",
                        "/login",
                        "/register",
                        "/css/*",
                        "/js/*",
                        "/h2-console/*"
                )
                    .permitAll()
                .antMatchers("/home")
                    .authenticated()
                .antMatchers("/admin/**")
                    .hasAnyRole(ADMIN.name(), SUPER_ADMIN.name())
                .and()
                .formLogin()
                    .loginPage("/login")
                    .loginProcessingUrl("/authenticateTheUser")
                    .defaultSuccessUrl("/")
                    .and()
                    .logout()
                    .permitAll();
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        final DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(webUserDetailsService);
        return provider;
    }
}
