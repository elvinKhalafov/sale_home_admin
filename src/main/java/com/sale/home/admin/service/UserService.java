package com.sale.home.admin.service;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public interface UserService extends UserDetailsService {





    @Override
    UserDetails loadUserByUsername(String s) throws UsernameNotFoundException;

}
