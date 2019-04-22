package com.sale.home.admin.service;

import com.sale.home.admin.model.Post;
import com.sale.home.admin.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {


    private PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }


    @Override
    public List<Post> getAllPosts(String status) {
        return postRepository.getAllPosts(status);
    }

    @Override
    public void deletePost(int id) {

         postRepository.deletePost(id);
    }

    @Override
    public Post getPostById(int id) {
        return postRepository.getPostById(id);
    }

    @Override
    public void updatePostStatus(int id, String status) {
        postRepository.updatePostStatus(id, status);
    }


}
