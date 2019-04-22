package com.sale.home.admin.controller;

import com.sale.home.admin.constants.UserConstants;
import com.sale.home.admin.model.User;
import com.sale.home.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/user/getAllActiveUsers")
    @ResponseBody
    public List<User> getAllActiveUsers(){
        List<User>users = userService.getAllUsersByStatus(UserConstants.USER_STATUS_ACTIVE);
        return users;

    }
    @RequestMapping("/user/getAllBlockedUsers")
    @ResponseBody
    public List<User> getAllBlockedUsers(){
        List<User>users = userService.getAllUsersByStatus(UserConstants.USER_STATUS_BLOCKED);
        return users;
    }

    @RequestMapping("/user/getAllInactiveUsers")
    @ResponseBody
    public List<User> getAllInactiveUsers(){
        List<User>users = userService.getAllUsersByStatus(UserConstants.USER_STATUS_INACTIVE);
        return users;
    }

    @RequestMapping("/user/user-detail/{id}")
    public String getUserDetail(@PathVariable("id") int id, Model model){
        User user = userService.getUserDetail(id);
        model.addAttribute("user", user);
        return "response/user-detail";
    }


    @RequestMapping("/user/update-user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateUserStatus(@PathVariable("id") int id, @RequestParam("status") int status) {
        userService.updateUserStatus(id, status);

    }

    @RequestMapping("/user/delete-user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUserById(@PathVariable("id") int id) {
        System.out.println(id);
        userService.deleteUserById(id);

    }

}
