package de.synyx.selfservice.auth.config;

import de.synyx.selfservice.auth.security.SecurityOrder;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.core.annotation.Order;

import org.springframework.ldap.core.support.LdapContextSource;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


/**
 * Created by klem on 13.04.15.
 */
@Configuration
@Order(SecurityOrder.OVERRIDE_DEFAULT_ORDER)
public class LoginConfig extends WebSecurityConfigurerAdapter {

    @Value(value = "${ldap.url}")
    private String ldapUrl;

    @Value(value = "${ldap.base}")
    private String ldapBase;

    @Value(value = "${ldap.userSearchBase}")
    private String ldapUserSearchBase;

    @Value(value = "${ldap.groupSearchBase}")
    private String ldapGroupSearchBase;

    @Value(value = "${ldap.groupSearchFilter}")
    private String ldapGroupSearchFilter;

    @Value(value = "${ldap.userDnPatterns}")
    private String ldapUserDnPatterns;

    @Bean
    public LdapContextSource contextSource() {

        LdapContextSource contextSource = new LdapContextSource();

        contextSource.setUrl(ldapUrl);
        contextSource.setBase(ldapBase);
        contextSource.setPooled(true);

        return contextSource;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception { // NOSONAR

        // auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
        auth.ldapAuthentication()
            .contextSource(contextSource())
            .userDnPatterns(ldapUserDnPatterns)
            .groupSearchBase(ldapGroupSearchBase)
            .groupSearchFilter(ldapGroupSearchFilter)
            .userSearchBase(ldapUserSearchBase);
    }


    @Override
    public void configure(HttpSecurity http) throws Exception { // NOSONAR

        http.formLogin()
            .loginPage("/login")
            .permitAll()
            .and()
            .logout()
            .deleteCookies("JSESSIONID")
            .and()
            .requestMatchers()
            .antMatchers("/login", "/oauth/authorize", "/oauth/confirm_access")
            .and()
            .authorizeRequests()
            .anyRequest()
            .authenticated();
    }
}
