package com.sale.home.admin.service;



import com.sale.home.admin.model.AdvancedSearchPost;
import com.sale.home.admin.model.Post;

import java.util.List;

public interface PostService {
    List<Post> searchPost(AdvancedSearchPost advancedSearchPost);
    List<Post> getRecentlyPost();
    void addPost(Post post);
    Post getPostById(int id);

}
