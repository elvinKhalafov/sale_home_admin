package com.sale.home.admin.controller;


import com.sale.home.admin.constants.PostConstants;
import com.sale.home.admin.model.Post;
import com.sale.home.admin.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PostController {


    @Autowired
    private PostService postService;

    @RequestMapping(value = "/post/getAllActivePosts", produces = "application/json")
    @ResponseBody
    public List<Post> getAllActivePosts(){
        return postService.getAllPosts(PostConstants.POST_STATUS_ACTIVE);
    }


    @RequestMapping("/post/getAllPendingPosts")
    @ResponseBody
    public List<Post> getAllPendingPosts(){
        List<Post> posts = postService.getAllPosts(PostConstants.POST_STATUS_INACTIVE);
        return posts;
    }

    @RequestMapping("/post/post-detail/{id}")
    public String getPostDeatil(@PathVariable("id") int id, Model model){
        Post post = postService.getPostById(id);
        model.addAttribute("post", post);
        return "response/post-detail";
    }

    @RequestMapping("/post/delete-post/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deletePost(@PathVariable("id") int id, Model model){
        postService.deletePost(id);
    }

    @RequestMapping("/post/update-post/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void inactivatePost(@PathVariable("id") int id, @RequestParam("status") String status){
        postService.updatePostStatus(id, status);
    }


}
