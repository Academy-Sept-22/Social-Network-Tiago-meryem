package com.socialnetwork.repos;

import java.util.List;

public interface PostRepository {
    void add(Post post);

    List<Post> getPosts(String userName);
}
