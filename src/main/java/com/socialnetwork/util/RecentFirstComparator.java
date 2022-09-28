package com.socialnetwork.util;

import com.socialnetwork.repos.Post;

import java.util.Comparator;

public class RecentFirstComparator implements Comparator<Post> {
    @Override
    public int compare(Post post1, Post post2) {
        // reverse comparison
        return post2.getDateTime().compareTo(post1.getDateTime());
    }
}
