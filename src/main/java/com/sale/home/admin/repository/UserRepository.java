package com.sale.home.admin.repository;


import com.sale.home.admin.model.User;

import java.util.List;

public interface UserRepository {

    User loginUser(String email);
    List<User>getAllUsers(int status);
    void blockUser(int id);
    void activateUser(int id);





}
