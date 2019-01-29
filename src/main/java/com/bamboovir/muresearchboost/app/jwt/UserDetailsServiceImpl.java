package com.bamboovir.muresearchboost.app.jwt;

import com.bamboovir.muresearchboost.app.persistence.TokenUserRepository;
import com.bamboovir.muresearchboost.model.TokenUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private TokenUserRepository tokenUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        TokenUser tokenUser = tokenUserRepository.findById(username).block();

        if (tokenUser == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            return SecurityModelFactory.create(tokenUser);
        }
    }
}
