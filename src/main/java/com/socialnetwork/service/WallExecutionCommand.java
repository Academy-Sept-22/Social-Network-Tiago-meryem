package com.socialnetwork.service;

import com.socialnetwork.command.Command;
import com.socialnetwork.repos.Post;
import com.socialnetwork.repos.PostRepository;
import com.socialnetwork.repos.User;
import com.socialnetwork.repos.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class WallExecutionCommand extends ExecutionCommand {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final PostPrinter postPrinter;

    public WallExecutionCommand(UserRepository userRepository, PostRepository postRepository, PostPrinter postPrinter) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.postPrinter = postPrinter;
    }

    @Override
    public void execute(Command command) {
        if (userRepository.checkIfExists(command.getUserName())) {
            User user = userRepository.get(command.getUserName());
            List<Post> posts = joinAllPosts(user);
            postPrinter.printAllPosts(posts);
        }
    }

    private List<Post> joinAllPosts(User user) {
        List<Post> posts = new ArrayList<>(postRepository.getPosts(user.getUserName()));
        user.follows().forEach(followedUser -> posts.addAll(postRepository.getPosts(followedUser.getUserName())));
        return posts;
    }
}
