package com.socialnetwork.repos;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

class PostRepositoryShould {

    private PostRepository postRepository;

    @BeforeEach
    public void setup(){
        postRepository = new PostRepository();
    }

    @Test
    public void add_a_new_post(){
        LocalDateTime currentDateTime = LocalDateTime.of(2022, 9, 1, 12, 0, 0);
        Post post = new Post("Alice",
                "I love the weather today",
                currentDateTime);
        postRepository.add(post);
        Assertions.assertEquals(List.of(post),  postRepository.getPosts("Alice"));
    }

    @Test
    public void add_two_posts_for_the_same_user(){
        LocalDateTime currentDateTime = LocalDateTime.of(2022, 9, 1, 12, 0, 0);
        Post post = new Post("Alice",
                "I love the weather today",
                currentDateTime);
        Post post2 = new Post("Alice",
                "I love the weather today",
                currentDateTime.plusSeconds(2));
        postRepository.add(post);
        postRepository.add(post2);
        Assertions.assertEquals(List.of(post, post2),  postRepository.getPosts("Alice"));
    }

}