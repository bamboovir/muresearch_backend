package com.bamboovir.muresearchboost.app.jwt;

import com.bamboovir.muresearchboost.model.TokenUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.Collection;
import java.util.Date;

public class SecurityModelFactory {
    public static UserDetailImpl create(TokenUser tokenUser) {
        Collection<? extends GrantedAuthority> authorities;
        try {
            authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(tokenUser.getAuthorities());
        } catch (Exception e) {
            authorities = null;
        }

        Date lastPasswordReset = new Date();
        return new UserDetailImpl(
                tokenUser.getUsername(),
                tokenUser.getUsername(),
                tokenUser.getPassword(),
                lastPasswordReset,
                authorities,
                tokenUser.getEnable()
        );
    }

}
