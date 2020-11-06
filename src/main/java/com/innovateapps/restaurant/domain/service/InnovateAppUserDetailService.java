package com.innovateapps.restaurant.domain.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class InnovateAppUserDetailService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username)throws UsernameNotFoundException{
        return new User("javier","{noop}Innova12345", new ArrayList<>());
    }
}
