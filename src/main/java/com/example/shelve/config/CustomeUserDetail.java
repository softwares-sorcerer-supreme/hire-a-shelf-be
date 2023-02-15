package com.example.shelve.config;

import com.example.shelve.entities.Account;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class CustomeUserDetail implements UserDetails {

    private Account account;

    public CustomeUserDetail(Account account) {
        this.account = account;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(account.getAdmin() != null) return Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN"));
        if(account.getStore() != null) return Collections.singleton(new SimpleGrantedAuthority("ROLE_STORE"));
        if(account.getBrand() != null) return Collections.singleton(new SimpleGrantedAuthority("ROLE_BRAND"));
        return Collections.singleton(new SimpleGrantedAuthority(""));
    }

    @Override
    public String getPassword() {
        return account.getPassword();
    }

    @Override
    public String getUsername() {
        return account.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return account.isStatus();
    }
}
