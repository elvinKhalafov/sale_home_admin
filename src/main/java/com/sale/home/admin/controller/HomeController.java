package com.sale.home.admin.controller;

import com.sale.home.admin.model.Post;
import com.sale.home.admin.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private PostService postService;

    @RequestMapping("/")
    public String index(Model model) {
        List<Post> postList = postService.;
        model.addAttribute("postList", postList);
        System.out.println(postList);
        return "view/active-topics;
    }
}
