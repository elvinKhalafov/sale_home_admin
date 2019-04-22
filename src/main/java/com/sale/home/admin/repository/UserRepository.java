package com.sale.home.admin.repository;


import com.sale.home.admin.model.User;

import java.util.List;

public interface UserRepository {

    User loginUser(String email);

    List<User> getAllUsersByStatus(int status);

    User getUserDetail(int id);

    void updateUserStatus(int id, int status);

    void deleteUserById(int id);




}
