package com.sale.home.admin.repository;



import com.sale.home.admin.model.AdvancedSearchPost;
import com.sale.home.admin.model.Post;

import java.util.List;

public interface PostRepository {

    List<Post> searchPost(AdvancedSearchPost advancedSearchPost);
    void addPost(Post post);
    Post getPostById(int id);
    List<Post> getRecentlyPost();


}
