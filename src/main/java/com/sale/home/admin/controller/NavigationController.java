package com.sale.home.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NavigationController {


    @RequestMapping("/login")
    public String openLoginPage() {

        return "view/login";
    }

    @RequestMapping("/pending-topic")
    public String openPendingTopicPage() {

        return "view/pending-topic";
    }

    @RequestMapping("/active-users")
    public String openActiveUsersPage() {

        return "view/active-users";
    }

    @RequestMapping("/blocked-users")
    public String openBlockedUsersPage() {

        return "view/blocked-users";
    }

    @RequestMapping("/blank")
    public String openBlankPage() {

        return "view/blank";
    }



}
