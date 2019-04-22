package com.sale.home.admin.service;


import com.sale.home.admin.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;


public interface UserService extends UserDetailsService {

    List<User>getAllUsersByStatus(int status);

    User getUserDetail(int id);

    void updateUserStatus(int id, int status);

    void deleteUserById(int id);

    @Override
    UserDetails loadUserByUsername(String s) throws UsernameNotFoundException;

}
