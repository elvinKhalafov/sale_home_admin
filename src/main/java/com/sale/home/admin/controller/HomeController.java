package com.sale.home.admin.controller;

import com.sale.home.admin.service.PostService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class HomeController {
    @Autowired
    private PostService postService;


    @RequestMapping("/")
    public String index(JoinPoint joinPoint) {

        System.out.println(joinPoint);
        return "view/active-topics";
    }
}
