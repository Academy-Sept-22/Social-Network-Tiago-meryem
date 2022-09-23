package com.socialnetwork.service;

import com.socialnetwork.command.Command;
import com.socialnetwork.repos.Post;
import com.socialnetwork.repos.PostRepository;
import com.socialnetwork.repos.User;
import com.socialnetwork.repos.UserRepository;
import com.socialnetwork.util.ClockService;

public class PostExecutionCommand extends ExecutionCommand {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final ClockService clockService;

    public PostExecutionCommand(UserRepository userRepository,
                                PostRepository postRepository,
                                ClockService clockService) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.clockService = clockService;
    }

    @Override
    public void execute(Command command) {

        if (!userRepository.checkIfExists(command.getUserName())) {
            userRepository.add(new User(command.getUserName()));
        }

        postRepository.add(new Post(command.getUserName(),
                command.getMessage(), clockService.getCurrentTime()));

    }
}