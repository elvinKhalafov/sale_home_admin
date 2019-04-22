package com.sale.home.admin.repository;


import com.sale.home.admin.model.Post;

import java.util.List;

public interface PostRepository {

    List<Post> getAllPosts(String status);

    void deletePost(int id);

    Post getPostById(int id);

    void updatePostStatus(int id, String status);


}
