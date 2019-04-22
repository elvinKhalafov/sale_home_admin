package com.sale.home.admin.service;


import com.sale.home.admin.model.User;
import com.sale.home.admin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUsersByStatus(int status) {
        return userRepository.getAllUsersByStatus(status);
    }

    @Override
    public User getUserDetail(int id) {
        return userRepository.getUserDetail(id);
    }

    @Override
    public void updateUserStatus(int id, int status) {
        userRepository.updateUserStatus(id, status);
    }

    @Override
    public void deleteUserById(int id) {
        userRepository.deleteUserById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.loginUser(username);

        if(user==null){
            throw new UsernameNotFoundException(username);
        }
        return user;
    }
}
