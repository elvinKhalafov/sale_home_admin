package com.sale.home.admin.controller;

import com.sale.home.admin.constants.UserConstants;
import com.sale.home.admin.model.User;
import com.sale.home.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.websocket.server.PathParam;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/user/getAllActiveUsers")
    @ResponseBody
    public List<User> getAllActiveUsers(){
        List<User>users = userService.getAllUsers(UserConstants.USER_STATUS_ACTIVE);
        return users;

    }
    @RequestMapping("/user/getAllBlockedUsers")
    @ResponseBody
    public List<User> getAllBlockedUsers(){
        List<User>users = userService.getAllUsers(UserConstants.USER_STATUS_BLOCKED);
        return users;
    }

    @RequestMapping("/user/blocUserByid")
    public ResponseEntity blockUser(@PathParam("id") int id){
      userService.blockUser(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping("/user/activateUserById")
    public ResponseEntity activateUser(@PathParam("id") int id){
         userService.activateUser(id);
        return new ResponseEntity(HttpStatus.OK);
    }



    }
