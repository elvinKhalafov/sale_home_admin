package com.sale.home.admin.service;


import com.sale.home.admin.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;


public interface UserService extends UserDetailsService {
    List<User>getAllUsers(int status);
    void blockUser(int id);
    void activateUser(int id);


    @Override
    UserDetails loadUserByUsername(String s) throws UsernameNotFoundException;

}
