package com.sale.home.admin.service;


import com.sale.home.admin.model.User;
import com.sale.home.admin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.loginUser(username);

        if(user==null){
            throw new UsernameNotFoundException(username);
        }
        return user;
    }
}
