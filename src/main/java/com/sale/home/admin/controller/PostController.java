package com.sale.home.admin.controller;


import com.sale.home.admin.constants.PostConstants;
import com.sale.home.admin.model.Post;
import com.sale.home.admin.service.PostService;
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
public class PostController {


    @Autowired
    private PostService postService;

    @RequestMapping("/post/getAllActivePosts")
    @ResponseBody
    public List<Post> getAllActivePosts(){
        List<Post>posts = postService.getAllPosts(PostConstants.POST_STATUS_ACTIVE);
        return posts;
    }

    @RequestMapping("/post/deletePost/{id}")
    public ResponseEntity deletePost(@PathParam("id") int id){

        postService.deletePost(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping("/post/getAllPendingPosts")
    @ResponseBody
    public List<Post> getAllPendingPosts(){
        List<Post>posts = postService.getAllPosts(PostConstants.POST_STATUS_INACTIVE);
        return posts;
    }


    @RequestMapping("/post/activatePostById/{id}")
    public ResponseEntity activatePost(@PathParam("id") int id){
        postService.activatePost(id);

        return new ResponseEntity(HttpStatus.OK);
    }


}
