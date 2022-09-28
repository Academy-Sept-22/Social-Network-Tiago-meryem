package com.socialnetwork.repos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PostInMemoryRepository implements PostRepository {

    private final HashMap<String, List<Post>> posts = new HashMap<>();
    @Override
    public void add(Post post) {
        posts.computeIfAbsent(post.getUserName(),  item -> new ArrayList<>()).add(post);
    }

    @Override
    public List<Post> getPosts(String userName) {
        return posts.get(userName);
    }
}
