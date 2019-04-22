package com.sale.home.admin.controller;

import com.sale.home.admin.model.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NavigationController {


    @RequestMapping("/login")
    public String openLoginPage(@AuthenticationPrincipal User user) {
        if ( user == null)
            return "view/login";
        else
            return "redirect:/";
    }

    @RequestMapping("/blank")
    public String openBlankPage() {

        return "view/blank";
    }


}
