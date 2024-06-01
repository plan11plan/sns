package com.example.sns.common.config;

import com.example.sns.core.user.domain.entity.root.User;
import java.io.Serializable;
import java.util.List;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserPrincipal implements UserDetails, Serializable {
    private static final long serialVersionUID = 1L; // 고정된 serialVersionUID 설정
    private final Long userId;
    private final String email;
    private final String password;
    private final List<SimpleGrantedAuthority> authorities;

    public UserPrincipal(User user) {
        this.userId = user.getUserIdValue();
        this.email = user.getEmailValue();
        this.password = user.getPasswordValue();
        this.authorities = List.of(
                new SimpleGrantedAuthority("ROLE_USER"),
                new SimpleGrantedAuthority(user.getRoleValue())
        );
    }

    public Long getUserId() {
        return userId;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public List<SimpleGrantedAuthority> getAuthorities() {
        return authorities;
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
        return true;
    }
}
