package com.socialnetwork.service;

import com.socialnetwork.command.Command;
import com.socialnetwork.repos.Post;
import com.socialnetwork.repos.PostRepository;
import com.socialnetwork.repos.UserRepository;

import java.util.List;

public class ReadExecutionCommand extends ExecutionCommand {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final PostPrinter postPrinter;

    public ReadExecutionCommand(UserRepository userRepository,
                                PostRepository postRepository,
                                PostPrinter postPrinter) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.postPrinter = postPrinter;
    }

    @Override
    public void execute(Command command) {
        if (userRepository.checkIfExists(command.getUserName())) {
            List<Post> posts = postRepository.getPosts(command.getUserName());
            postPrinter.printAllPosts(posts);
        }
    }

}