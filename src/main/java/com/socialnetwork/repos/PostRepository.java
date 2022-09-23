package com.socialnetwork.repos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PostRepository {

    HashMap<String, List<Post>> posts = new HashMap<>();
    public void add(Post post) {
        posts.computeIfAbsent(post.getUserName(),  item -> new ArrayList<>()).add(post);
    }

    public List<Post> getPosts(String userName) {
        return posts.get(userName);
    }
}
